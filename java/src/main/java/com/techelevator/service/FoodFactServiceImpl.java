package com.techelevator.service;

import com.techelevator.model.Food;
import com.techelevator.model.FoodDto;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import org.springframework.web.client.ResourceAccessException;
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
    private static final String API_NINJA_KEY = "X-Api-Key";
    private static final String API_NINJA_VALUE = "ZTiKebEmbGDPgIwUgnV3XQ==biRhU7uo94M8dOUL";
    private RestTemplate restTemplate = new RestTemplate();
//    private String X_API_KEY = "ZTiKebEmbGDPgIwUgnV3XQ==biRhU7uo94M8dOUL";

    private final JdbcTemplate jdbcTemplate;


    public FoodFactServiceImpl( JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private HttpEntity<FoodDto> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(API_NINJA_KEY, API_NINJA_VALUE);
//        headers.set(API_NINJA_KEY, API_NINJA_VALUE);
        return new HttpEntity<>(headers);
    }

    public Food[] getFacts(String name, boolean useWildCard){
        FoodDto[] foodDto;
        Food[] food = null;
        if(useWildCard){
            name = "%" + name + "%";
        }

        ResponseEntity<FoodDto[]> response =
                restTemplate.exchange(API_URL + name, HttpMethod.GET,
                        makeAuthEntity(), FoodDto[].class);
        foodDto = response.getBody();

        for (int i = 0; i < foodDto.length - 1; i++){

            Food foodInNewArray = mapFoodDtoToFood(foodDto[i]);
            food[i++] = foodInNewArray;

        }
//        for(FoodDto prop : foodDto){
//            mapFoodDtoToFood(prop);
//        }
        //Loop through foodDto(foreach loop)
        //Pass each foodDto to mapFoodDtoToFood
        //That returns back a food
        //That food will be added to the food array created up top

        return food;
    }

    private Food mapFoodDtoToFood(FoodDto foodDto) {
        Food food = new Food();
//        foodDto.setFoodId(fd.getInt("food_id"));
        food.setFoodName(foodDto.getFoodName());
//        foodDto.setFoodType(fd.getString("food_type"));
        food.setServingSize(foodDto.getServingSize());
        food.setCalories(foodDto.getCalories());
        return food;
    }




//    public FoodDto getFacts() throws RestClientResponseException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("X-API-KEY", "ZTiKebEmbGDPgIwUgnV3XQ==biRhU7uo94M8dOUL");
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        ResponseEntity<FoodDto> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, FoodDto.class);
//        FoodDto foodFact = response.getBody();
//        if (foodFact != null) {
//
//            String sql = "INSERT INTO  food (food_name, food_type, serving_size, calories) VALUES(?, ?, ?, ?)";
//            jdbcTemplate.update(sql, foodFact.getFoodName(), foodFact.getFoodType(), foodFact.getServingSize(), foodFact.getCalories());
//
//        }
//        return foodFact;
//    }










}
