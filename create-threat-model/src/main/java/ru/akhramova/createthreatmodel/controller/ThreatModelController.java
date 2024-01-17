package ru.akhramova.createthreatmodel.controller;

import dto.Danger;
import dto.ModelDto;
import dto.ProbabilityOfImplementation;
import dto.ThreatNodeDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akhramova.createthreatmodel.entity.*;
import ru.akhramova.createthreatmodel.service.ThreatModelService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Threat model Controller",
        description = "Сервис для автоматизированного построения модели угроз безопасности информации")
public class ThreatModelController {

    private final ThreatModelService threatModelService;

    private static List<ThreatNodeDto> nodes = new ArrayList<>();
    private static List<TargetEntity> currentTargetsList = new ArrayList<>();
    private static List<SourceEntity> currentSourcesList = new ArrayList<>();

    @ModelAttribute("probs")
    public List<String> probabilitiesOfImplementation() {
        return Arrays.asList("0", "0.2", "0.5", "1");
    }

    @ModelAttribute("dangers")
    public List<String> dangers() {
        return Arrays.asList("0.2", "0.6", "1");
    }

    @GetMapping
    public String getModels(Model model) {
        currentTargetsList.clear();
        currentSourcesList.clear();
        model.addAttribute("models", threatModelService.getAllModels());
        return "models/models";
    }

    @GetMapping("/targets")
    public String createModel(Model model) {
        model.addAttribute("targets", threatModelService.getAllTargets());
        return "models/target_select_page";
    }

    @PostMapping("/sources")
    public String selectSources(@RequestParam(value = "tars", required = false) int[] targets, Model model) {
        if (targets != null) {
            currentTargetsList.addAll(threatModelService.getTargetsByIds(Arrays.stream(targets).boxed().map(Long::valueOf).toList()));
        }
        model.addAttribute("sources", threatModelService.getAllSources());
        return "models/source_select_page";
    }

    @PostMapping("/coefficients")
    public String setCoefficients(@RequestParam(value = "sours", required = false) int[] sources, Model model) {
        if (sources != null) {
            currentSourcesList.addAll(threatModelService.getSourcesByIds(Arrays.stream(sources).boxed().map(Long::valueOf).toList()));
        }
        nodes = threatModelService.getNodes(currentTargetsList, currentSourcesList);
        model.addAttribute("nodes", nodes);
        return "models/set_coefficients_page";
    }

    @PostMapping("/preview")
    public String previewModel(Model model) {
        ModelDto modelDto = new ModelDto();
        modelDto.setNodes(nodes);
        model.addAttribute("modelData", modelDto);
        return "models/preview_page";
    }

    @PostMapping("/save")
    public String saveModel(@ModelAttribute ModelDto modelDto, Model model) {
        modelDto.setNodes(nodes);
        threatModelService.saveModel(modelDto);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String editModel(@PathVariable Long id) {
        threatModelService.editModel(id);
        return "redirect:/";
    }

    @PostMapping("/download/{id}")
    public String downloadModel(@PathVariable Long id) {
        threatModelService.downloadModel(id);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteModel(@PathVariable Long id) {
        threatModelService.deleteModel(id);
        return "redirect:/";
    }

}
