package com.techelevator.service;

import com.techelevator.model.Food;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class FoodFactServiceImpl {

    // Ninja Calories API Key = ZTiKebEmbGDPgIwUgnV3XQ==biRhU7uo94M8dOUL
    // USDA API Key = 3X2x7kenmnPndnhsXgVeDksPa6b6NyVc4SEel5zj
    // FastsecretPlatform API KEY = cf3db894195c404fa60cd249f20c5330
    // private static final String API_URL = "https://api.nal.usda.gov/fdc/v1/food";
    // private static final String API_URL = "https://platform.fatsecret.com/rest/server.api";
    // private static final String API_URL = "https://api.api-ninjas.com/v1/nutrition?query=";

    private static final String API_URL ="";
    private RestTemplate restTemplate = new RestTemplate();
    public Food getFacts() throws RestClientResponseException{
        Food foodFact = restTemplate.getForObject(API_URL, Food.class);
        return foodFact;
    }
}
