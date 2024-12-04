package helenocampos.github.io.fitapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import helenocampos.github.io.fitapp.model.ImcGenderResult;
import helenocampos.github.io.fitapp.model.ImcResult;
import helenocampos.github.io.fitapp.service.FeatureFlagService;
import helenocampos.github.io.fitapp.service.GenderService;

@Controller
public class ImcController {

    GenderService genderService;

    FeatureFlagService featureFlagService;

    @Autowired
    public ImcController(GenderService genderService, FeatureFlagService featureFlagService) {
        this.genderService = genderService;
        this.featureFlagService = featureFlagService;
    }

    @GetMapping("/imc")
    public String imcForm() {
        if (featureFlagService.isEnabled("newImcForm")){
            return "newImcForm";
        }else{
            return "imcForm";
        }
    }

    @GetMapping("/imcGender")
    public String imcGenderForm() {
        return "imcGenderForm";
    }

    @PostMapping("/imc")
    public String proccessImcRequest(@RequestParam("peso") double peso,
            @RequestParam("altura") double altura,
            Model model) {

        ImcResult resultado = proccessImc(peso, altura);

        model.addAttribute("imc", resultado.getImc());
        model.addAttribute("resultado", resultado.getClassification());
        if (featureFlagService.isEnabled("newImcForm")){
            return "newImcForm";
        }else{
            return "imcForm";
        }
    }

    public ImcResult proccessImc(double peso, double altura) {
        double imc = this.getIMC(peso, altura);
        String resultado = this.getIMCClassification(imc);
        return new ImcResult(resultado, imc);
    }

    public ImcGenderResult proccessImcGender(double peso, double altura, String nome) {
        double imc = this.getIMC(peso, altura);
        String gender = genderService.getGenderByName(nome);
        String resultado = this.getIMCClassificationByGender(imc, gender);
        String genderURL = genderService.getExternalServiceUrl() + nome;
        return new ImcGenderResult(resultado, imc, gender, genderURL);
    }

    @PostMapping("/imcGender")
    public String processImcGenderRequest(@RequestParam("peso") double peso,
            @RequestParam("altura") double altura,
            @RequestParam("nome") String nome,
            Model model) {

        ImcGenderResult resultado = proccessImcGender(peso, altura, nome);

        model.addAttribute("genero", getGeneroPt(resultado.getGender()));
        model.addAttribute("imc", resultado.getImc());
        model.addAttribute("resultado", resultado.getClassification());
        model.addAttribute("url", resultado.getRequestUrl());
        return "imcGenderForm";
    }

    public String getGeneroPt(String gender) {
        if (gender.equals("male")) {
            return "Masculino";
        } else if (gender.equals("female")) {
            return "Feminino";
        } else {
            return "Indefinido";
        }
    }

    public double getIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    public String getIMCClassification(double imc) {
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

    public String getIMCClassificationByGender(double imc, String gender) {
        double thresholdAbaixoPeso = 20;
        double thresholdNormal = 24.9;
        double thresholdObesidadeLeve = 29.9;
        double thresholdObesidadeModerada = 39.9;
        if (gender.equals("female")) {
            thresholdAbaixoPeso -= 1;
            thresholdNormal -= 1;
            thresholdObesidadeLeve -= 1;
            thresholdObesidadeModerada -= 1;
        }

        if (imc < thresholdAbaixoPeso) {
            return "Abaixo do peso";
        } else if (imc <= thresholdNormal) {
            return "Peso normal";
        } else if (imc <= thresholdObesidadeLeve) {
            return "Obesidade leve";
        } else if (imc <= thresholdObesidadeModerada) {
            return "Obesidade moderada";
        } else {
            return "Obesidade mórbida";
        }
    }
}
