package helenocampos.github.io.fitapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class GenderService {

    private final RestTemplate restTemplate;

    public GenderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGenderByName(String name) {
        String url = "https://api.genderize.io?name=" + name;
        String result = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(result);
        return json.optString("gender", "Indefinido");
    }

    public String getExternalServiceUrl(){
        return "https://api.genderize.io?name=";   
    }
}
