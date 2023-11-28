package ru.akhramova.createthreatmodel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.akhramova.createthreatmodel.service.ThreatModelService;

@Controller
public class ThreatModelController {

    @Autowired
    private ThreatModelService threatModelService;

    @GetMapping
    public String getModels(Model model) {
        model.addAttribute("models", threatModelService.getAllModels());
        return "models/models";
    }

}
