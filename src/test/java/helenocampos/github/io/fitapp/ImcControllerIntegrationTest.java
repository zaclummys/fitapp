package helenocampos.github.io.fitapp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import helenocampos.github.io.fitapp.controller.ImcController;
import helenocampos.github.io.fitapp.service.FeatureFlagService;
import helenocampos.github.io.fitapp.service.GenderService;

public class ImcControllerIntegrationTest {

    private ImcController imcController;
    private GenderService genderService;
    FeatureFlagService featureFlagService;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        restTemplate = mock(RestTemplate.class);
        genderService = new GenderService(restTemplate);
        featureFlagService = new FeatureFlagService();
        imcController = new ImcController(genderService, featureFlagService);
    }

    @Test
    public void testImcAbaixoPeso(){
        String result = imcController.proccessImc(18.7, 1).getClassification();
        String expected = "Abaixo do peso";
        assertEquals(expected, result);
    }

}
