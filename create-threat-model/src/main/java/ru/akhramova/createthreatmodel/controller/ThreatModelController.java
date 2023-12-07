package ru.akhramova.createthreatmodel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akhramova.createthreatmodel.entity.ModelEntity;
import ru.akhramova.createthreatmodel.entity.SourceEntity;
import ru.akhramova.createthreatmodel.entity.TargetEntity;
import ru.akhramova.createthreatmodel.entity.ThreatNodeEntity;
import ru.akhramova.createthreatmodel.service.ThreatModelService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThreatModelController {

    private final ThreatModelService threatModelService;

    private static final List<ThreatNodeEntity> nodes = new ArrayList<>();
    private static final List<TargetEntity> currentTargetsList = new ArrayList<>();
    private static final List<SourceEntity> currentSourcesList = new ArrayList<>();

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
        model.addAttribute("nodes", threatModelService.getNodes(currentTargetsList, currentSourcesList));
        return "models/set_coefficients_page";
    }

    @PostMapping("/preview")
    public String previewModel(@ModelAttribute List<ThreatNodeEntity> nodes, Model model) {
        ModelEntity modelEntity = new ModelEntity();
        model.addAttribute("modelData", modelEntity);
        return "models/preview_page";
    }

    @PostMapping("/save")
    public String saveModel(@ModelAttribute ModelEntity model) {
        threatModelService.saveModel(model);
        return "redirect:models/models";
    }

    @PostMapping("/edit/{id}")
    public String editModel(@PathVariable Long id) {
        threatModelService.editModel(id);
        return "redirect:models/models";
    }

    @PostMapping("/download/{id}")
    public String downloadModel(@PathVariable Long id) {
        threatModelService.downloadModel(id);
        return "redirect:models/models";
    }

    @PostMapping("/delete/{id}")
    public String deleteModel(@PathVariable Long id) {
        threatModelService.deleteModel(id);
        return "redirect:models/models";
    }

}
