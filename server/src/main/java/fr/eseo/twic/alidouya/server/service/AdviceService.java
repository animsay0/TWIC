package fr.eseo.twic.alidouya.server.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdviceService {

    private final RestTemplate restTemplate;

    public AdviceService() {
        this.restTemplate = new RestTemplate();
    }

    public String getAdvice() {
        String url = "https://api.adviceslip.com/advice";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "{\"error\":\"Failed to fetch advice.\"}";
        }
    }
}
