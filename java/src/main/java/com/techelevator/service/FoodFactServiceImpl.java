package com.techelevator.service;

import com.techelevator.model.FoodDto;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
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

    private static final String API_URL ="https://api.api-ninjas.com/v1/nutrition?query=";
    private RestTemplate restTemplate = new RestTemplate();
    private String X_API_KEY = null;

    private final JdbcTemplate jdbcTemplate;


    public FoodFactServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




        public FoodDto getFacts() throws RestClientResponseException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "ZTiKebEmbGDPgIwUgnV3XQ==biRhU7uo94M8dOUL");
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<FoodDto> response =  restTemplate.exchange(API_URL, HttpMethod.GET,entity, FoodDto.class);
        FoodDto foodFact = response.getBody();
        return foodFact;
    }




}
