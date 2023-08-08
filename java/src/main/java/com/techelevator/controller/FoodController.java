package com.techelevator.controller;

import com.techelevator.dao.FoodDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FoodController {
    private FoodDao foodDao;

    public FoodController(FoodDao foodDao) {
        this.foodDao = foodDao;
    }
}
