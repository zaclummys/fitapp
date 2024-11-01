package helenocampos.github.io.fitapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import helenocampos.github.io.fitapp.controller.ImcController;
import helenocampos.github.io.fitapp.service.GenderService;

public class ImcControllerTest {
    private ImcController imcController;
    private GenderService genderServiceMock;

    @BeforeEach
    public void setUp() {
        genderServiceMock = Mockito.mock(GenderService.class);
        imcController = new ImcController(genderServiceMock);
    }

    @Test
    public void testImcValue() {
        double result = imcController.getIMC(70, 1.70);
        double expected = 24.22;
        assertEquals(expected, result, 0.002);
    }

    @Test
    public void testImcValueAltura1() {
        double result = imcController.getIMC(50, 1);
        double expected = 50;
        assertEquals(expected, result, 0.002);
    }

    @Test
    public void testgetIMCClassificationPesoNormal(){
        String result = imcController.getIMCClassification(24.9);
        String expected = "Peso normal";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationExcesso(){
        String result = imcController.getIMCClassification(29.8);
        String expected = "Excesso de peso";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationObesidade1(){
        String result = imcController.getIMCClassification(34.7);
        String expected = "Obesidade classe I";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationObesidade2(){
        String result = imcController.getIMCClassification(39.8);
        String expected = "Obesidade classe II";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationObesidade3(){
        String result = imcController.getIMCClassification(39.9);
        String expected = "Obesidade classe III";
        assertEquals(expected, result);
    }

    @Test
    public void testGetGeneroPtMale(){
        String result = imcController.getGeneroPt("male");
        String expected = "Masculino";
        assertEquals(expected, result);
    }

    @Test
    public void testGetGeneroPtFemale(){
        String result = imcController.getGeneroPt("female");
        String expected = "Feminino";
        assertEquals(expected, result);
    }

    @Test
    public void testGetGeneroPtIndefinido(){
        String result = imcController.getGeneroPt("");
        String expected = "Indefinido";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationByGenderAbaixoFemale(){
        String result = imcController.getIMCClassificationByGender(18.9, "female");
        String expected = "Abaixo do peso";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationByGenderNormalFemale(){
        String result = imcController.getIMCClassificationByGender(23.9, "female");
        String expected = "Peso normal";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationByGenderObesidadeLeveFemale(){
        String result = imcController.getIMCClassificationByGender(28.9, "female");
        String expected = "Obesidade leve";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationByGenderObesidadeModeradaFemale(){
        String result = imcController.getIMCClassificationByGender(38.9, "female");
        String expected = "Obesidade moderada";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationByGenderObesidadeMorbidaFemale(){
        String result = imcController.getIMCClassificationByGender(39, "female");
        String expected = "Obesidade mórbida";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationByGenderAbaixoMale(){
        String result = imcController.getIMCClassificationByGender(19.9, "male");
        String expected = "Abaixo do peso";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationByGenderNormalMale(){
        String result = imcController.getIMCClassificationByGender(24.9, "male");
        String expected = "Peso normal";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationByGenderObesidadeLeveMale(){
        String result = imcController.getIMCClassificationByGender(29.9, "male");
        String expected = "Obesidade leve";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationByGenderObesidadeModeradaMale(){
        String result = imcController.getIMCClassificationByGender(39.9, "male");
        String expected = "Obesidade moderada";
        assertEquals(expected, result);
    }

    @Test
    public void testgetIMCClassificationByGenderObesidadeMorbidaMale(){
        String result = imcController.getIMCClassificationByGender(40, "male");
        String expected = "Obesidade mórbida";
        assertEquals(expected, result);
    }
}
