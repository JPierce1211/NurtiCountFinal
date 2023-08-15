package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import com.techelevator.model.Meals;
import com.techelevator.model.Profile;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
@Component
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RestController
public class MealsController {
    private JdbcTemplate jdbcTemplate;
    private JdbcMealsDao mealsDao;
    private JdbcFoodDao foodDao;
    private JdbcUserDao userDao;

    public MealsController(JdbcMealsDao mealsDao, JdbcFoodDao foodDao, JdbcUserDao userDao) {
        this.mealsDao = mealsDao;
        this.foodDao = foodDao;
        this.userDao = userDao;
    }

    @GetMapping("/meals")
    public List<Meals> listMeals(Principal principal) {
        //Profile profile = profileDao.getProfileById(profileId);
        User user = userDao.getUserByUsername(principal.getName());
        if (user != null) {
            return mealsDao.findAll(user.getId());
        } else {
        }
        return null;
    }

    @GetMapping("/meals/{mealId}")
    public Meals get(@PathVariable int mealId, Principal principal) {
        //Profile profile = profileDao.getProfileById(profileId);
        User user = userDao.getUserByUsername(principal.getName());
        Meals meals = new Meals();
        if (user != null)
        {
            meals = mealsDao.getMealById(mealId);
            if (meals != null)
                {
                    return meals;
                }
        }
        return meals;
    }

    @GetMapping("/meals/{mealId}/calories")
    public int calTotal(@PathVariable int mealId, Principal principal, Meals meals){
        User user = userDao.getUserByUsername(principal.getName());
        meals.setUserId(user.getId());
        int meal = mealsDao.getTotalCalories(mealId);
        return meal;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createMeal")
    public Meals createMeal(@RequestBody Meals meals, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        if(user != null){
            return mealsDao.createMeal(meals);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/createMeal")
//    public Meals createMeal(@RequestBody Meals meals, List<Food> foodInMeal) {
//        Meals newMeal = null;
//        try {
//            newMeal = mealsDao.createMeal(meals, List<Food> foodInMeal);
//        }
//
//    }

        @PutMapping("/meals/{mealId}")
        public Meals update(Principal principal, @RequestBody Meals updatedMeal,@PathVariable int mealId){
            Meals newMeal = mealsDao.updateMealsById(updatedMeal, mealId);
            return newMeal;

        }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/meals/{mealId}")
        public void delete ( @PathVariable int mealId, Principal principal){
            User user = userDao.getUserByUsername(principal.getName());
            if (user != null) {
                mealsDao.deleteMealById(mealId);
            }
            if (mealsDao.deleteMealById(mealId) != 1) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Meal not found");
            }
        }
    }



