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
}
