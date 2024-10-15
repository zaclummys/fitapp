package helenocampos.github.io.fitapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImcController {

    @GetMapping("/imc")
    public String imcForm() {
        return "imcForm";
    }

    @PostMapping("/imc")
    public String calculateImc(@RequestParam("peso") double peso,
                               @RequestParam("altura") double altura,
                               Model model) {

        double imc = this.getIMC(peso, altura);
        String resultado = this.getIMCClassification(imc);

        model.addAttribute("imc", imc);
        model.addAttribute("resultado", resultado);

        return "imcForm";
    }

    // wip
    @PostMapping("/imcGender")
    public String calculateImcGender(@RequestParam("peso") double peso,
                               @RequestParam("altura") double altura,
                               @RequestParam("nome") String nome,
                               Model model) {

        double imc = this.getIMC(peso, altura);
        String resultado = this.getIMCClassification(imc);

        model.addAttribute("imc", imc);
        model.addAttribute("resultado", resultado);

        return "imcForm";
    }

    private double getIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    private String getIMCClassification(double imc){
        if (imc < 18.8) {
            return "Abaixo do peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Excesso de peso";
        } else if (imc < 34.9) {
            return "Obesidade classe I";
        } else if (imc < 39.9) {
            return "Obesidade classe II";
        } else {
            return "Obesidade classe III";
        }
    }
}
