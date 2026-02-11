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
            Map response = restTemplate.getForObject(url, Map.class);

            System.out.println("Twelve Data response: " + response);

            if (response == null || response.get("price") == null) {
                System.out.println("Twelve Data limit or error hit for " + symbol);
                return null;
            }

            return Double.parseDouble((String) response.get("price"));

        } catch (Exception e) {
            System.out.println("API error for " + symbol + ": " + e.getMessage());
            return null;
        }
    }

}
