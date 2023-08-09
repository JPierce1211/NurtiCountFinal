package com.techelevator.controller;

import com.techelevator.dao.FoodDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@Component
@RestController
@RequestMapping("/meals/food")
public class FoodController {

    private FoodDao foodDao;

    public FoodController(FoodDao foodDao) {
        this.foodDao = foodDao;

    }

    @GetMapping("")
    public List<Food> listAllFood() {
        return null;
    }

    @GetMapping("/{id}")
    public Food getFoodItem(@PathVariable int foodId) {
        Food food;
        try {
            food = foodDao.getFoodById(foodId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (food == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food item not found");
        }
        return food;
    }

    @DeleteMapping("/{id}")
    public void  deleteFoodById(@PathVariable int foodId) {
        try {
            int foodDeleted = foodDao.deleteFoodById(foodId);
            if (foodDeleted == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    // foodName was not added so this is just a place holder
//    @GetMapping("/food/food_name")
//    public FoodDao getFoodByName(String foodName) {
//        return null;
//    }
    }