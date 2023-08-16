package com.techelevator.controller;

import com.techelevator.dao.FoodDao;
import com.techelevator.dao.MealsDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import com.techelevator.model.FoodDto;
import com.techelevator.model.Meals;
import com.techelevator.service.FoodFactService;
import com.techelevator.service.FoodFactServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/meals/food")
public class FoodController {

    private FoodDao foodDao;
    private FoodDto foodDto;
    private FoodFactServiceImpl foodFactService;
    private MealsDao mealsDao;

    public FoodController(FoodDao foodDao, FoodFactServiceImpl foodFactService, MealsDao mealsDao) {
        this.foodDao = foodDao;
        this.foodFactService = foodFactService;
        this.mealsDao = mealsDao;

    }

    
    @GetMapping("")
    public List<Food> listAllFood() {
        try {
            return foodDao.listFood();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{foodId}")
    public Food getFoodById(@PathVariable int foodId) {
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

    @GetMapping("/mealFood/{mealId}")
    public List<Food> listFoodMeals(@PathVariable int mealId) {
        List<Food> allFood;
        try {
            allFood = foodDao.getFoodByMealId(mealId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (allFood == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food item not found");
        } return allFood;
    }

    @DeleteMapping("/{foodId}")
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

    @GetMapping("/supersearch") //supersearch?foodByName=
    public Food[] getFacts(@RequestParam(required = false) String foodByName, boolean userWildCard) {
        try {
            return foodFactService.getFacts(foodByName, userWildCard);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/foodName")
//    public List<Food> getFoodByName(@PathVariable String foodName) {
//        List<Food> food;
//        try {
//            food = foodDao.addFoodByName(foodName);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        if (food == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food item not found");
//        }
//        return food;
//
//    }


    @GetMapping("/foodByName") //foodByName?foodByName=
    public List<Food> getSpecificFood(@RequestParam(required = false) String foodByName, boolean useWildCard){
        List<Food> foodList;

//       if(useWildCard){
//           foodByName = "%" + foodByName + "%";
//        }
        try{
            foodList = foodDao.getFoodByName(foodByName,useWildCard);
        }catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (foodList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food item not found");
        } return foodList;
    }

//    @PostMapping("/{mealId}/addfood")
//    public Food addFoodToMeal(@RequestParam int mealId, int foodId, String logDay) {
//        try {
//        }
//
//    }

}