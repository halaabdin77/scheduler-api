package com.task1.scheduler.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class StockPriceService {

    @Value("${twelvedata.api.key}")
    private String apiKey;

    public Double getCurrentPrice(String symbol) {

        try {
            String url = String.format(
                    "https://api.twelvedata.com/price?symbol=%s&apikey=%s",
                    symbol, apiKey
            );

            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> response =
                    restTemplate.getForObject(url, Map.class);

            if (response == null || response.get("price") == null) {
                return null;
            }

            return Double.parseDouble(response.get("price"));

        } catch (Exception e) {
            return null;
        }
    }
}
