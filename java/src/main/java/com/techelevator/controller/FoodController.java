package com.techelevator.controller;

import com.techelevator.dao.FoodDao;
import com.techelevator.model.Food;
import com.techelevator.model.Meals;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    private FoodDao foodDao;

    public FoodController(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @GetMapping("")
    public List<Food> getAllFood(){
        return null;
    }

    @GetMapping("/{id}")
    public FoodDao getFoodById(int id){
        return null;
    }

    @GetMapping("/{id}")
    public FoodDao deleteFoodById(int id){
        return null;
    }

}