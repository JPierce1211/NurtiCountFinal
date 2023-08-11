package com.techelevator.controller;

import com.techelevator.dao.FoodDao;
import com.techelevator.dao.MealsDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import com.techelevator.model.FoodDto;
import com.techelevator.service.FoodFactService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@Component
@RestController
@RequestMapping("/meals/food")
public class FoodController {

    private FoodDao foodDao;

//    private FoodFactService foodFactService;

    public FoodController(FoodDao foodDao) {
        this.foodDao = foodDao;
//        this.foodFactService = foodFactService;
    }


    @GetMapping("")
    public List<Food> listAllFood() {
        try {
            return foodDao.listFood();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public Food getFoodItem(@PathVariable int foodId) {
        Food foodItem;
        try {
            foodItem = foodDao.getFoodById(foodId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (foodItem == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food item not found");
        }
        return foodItem;
    }

    @DeleteMapping("/{id}")
    public void deleteFoodById(@PathVariable int foodId) {
        try {
            int foodDeleted = foodDao.deleteFoodById(foodId);
            if (foodDeleted == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Food saveNewFood(FoodDto food) {
          try {
              return foodDao.addNewFood(food);
          } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }


//    // foodName was not added so this is just a place holder
//    @GetMapping("/food_name")
//    public FoodDao getFoodByName(String foodName) {
//        FoodDao food;

//    }
    }