package helenocampos.github.io.fitapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import helenocampos.github.io.fitapp.service.FeatureFlagService;

@Controller
public class FeatureController {

    FeatureFlagService featureFlagService;

    @Autowired
    public FeatureController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    @GetMapping("/example")
    public String exampleFeature(Model model) {
        boolean isNewFeatureEnabled = featureFlagService.isFeatureEnabled("tutorial-flag");

        if (isNewFeatureEnabled) {
            model.addAttribute("message", "A nova funcionalidade está ativada!");
        } else {
            model.addAttribute("message", "A funcionalidade padrão está em uso.");
        }

        return "featureExample";
    }
}
  
