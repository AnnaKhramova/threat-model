package ru.akhramova.createthreatmodel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.akhramova.createthreatmodel.entity.ModelEntity;
import ru.akhramova.createthreatmodel.entity.ThreatNodeEntity;
import ru.akhramova.createthreatmodel.service.ThreatModelService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThreatModelController {

    @Autowired
    private ThreatModelService threatModelService;

    private static List<ThreatNodeEntity> nodes = new ArrayList<>();

    @GetMapping
    public String getModels(Model model) {
        model.addAttribute("models", threatModelService.getAllModels());
        return "models/models";
    }

    @GetMapping("/new")
    public String createModel(Model model) {
        ModelEntity modelEntity = new ModelEntity();
        model.addAttribute("modelData", modelEntity);
        return "models/new_model";
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
