package helenocampos.github.io.fitapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import helenocampos.github.io.fitapp.service.GenderService;

public class GenderServiceTest {

    @InjectMocks
    private GenderService genderService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenderByName() {
        String name = "João";
        String mockResponse = "{\"count\":86489,\"name\":\"" + name + "\",\"gender\":\"male\",\"probability\":0.99}";

        when(restTemplate.getForObject("https://api.genderize.io?name=" + name, String.class)).thenReturn(mockResponse);

        String result = genderService.getGenderByName(name);
        String expected = "male";

        assertEquals(expected, result);
    }
}
