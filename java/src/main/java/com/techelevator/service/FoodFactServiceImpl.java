package com.techelevator.service;

import com.techelevator.model.Food;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class FoodFactServiceImpl {

    private static final String API_URL = "";
    private RestTemplate restTemplate = new RestTemplate();
    public Food getFacts() throws RestClientResponseException{
        Food foodFact = restTemplate.getForObject(API_URL, Food.class);
        return foodFact;
    }
}
