package com.techelevator.controller;

import com.techelevator.dao.JdbcMealsDao;
import com.techelevator.dao.JdbcProfileDao;
import com.techelevator.dao.ProfileDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Meals;
import com.techelevator.model.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
@Component
@PreAuthorize("isAuthenticated()")
@RestController
public class MealsController {
    private JdbcTemplate jdbcTemplate;
    private JdbcMealsDao mealsDao;
    private ProfileDao profileDao;
    public MealsController(JdbcMealsDao mealsDao, JdbcProfileDao profileDao){
        this.mealsDao = mealsDao;
        this.profileDao = profileDao;
    }

    @GetMapping("/profile/{id}/meals")
    public List<Meals> listMeals(@PathVariable int profileId){
        Profile profile = profileDao.getProfileById(profileId);
        if(profile != null){
            return mealsDao.findAllById();
        }else{
        }
            return null;
    }
    @GetMapping("/profile/{id}/meals/{id}")
    public Meals get(@PathVariable int profileId, @PathVariable int mealsId) {
        Profile profile = profileDao.getProfileById(profileId);

        if (profile != null) {
            Meals meals = mealsDao.getMealByProfile(mealsId);
            return meals;
        } else {
            return null;
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/profile/{id}/create-meal")
    public Meals createMeal(@PathVariable int profileId, @RequestBody Meals meals){
        Profile profile = profileDao.getProfileById(profileId);
        if(profile != null){
            return mealsDao.createMeal(meals);
        }else{
            return null;
        }

    }

    @PutMapping("/profile/{id}/meals/{id}")
    public Meals update(@PathVariable int profileId, @RequestBody Meals updatedMeal, @PathVariable int mealId){
        Profile profile = profileDao.getProfileById(profileId);
        if(profile != null){
            Meals updatedMeals = mealsDao.getMealByProfile(mealId);
            if(updatedMeals != null) {
                updatedMeal.setProfileId(profileId);
                updatedMeal.setMealId(mealId);
                updatedMeal.setMealDate(updatedMeal.getMealDate());
                updatedMeal.setMealType(updatedMeal.getMealType());

                try {
                    Meals updateMeal = mealsDao.updateMealsById(updatedMeal);
                    return updateMeal;
                } catch (DaoException e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update meal");
                }
//                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found");
                }
            }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        }
               return updatedMeal;
            }

    //meals.setMealId(mealId);
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/profile/{id}/meals/{id}")
    public void delete(@PathVariable int mealId, @PathVariable int profileId){
        Profile profile = profileDao.getProfileById(profileId);
        if(profile != null){
            int deleteMeal = mealsDao.deleteMealById(mealId);
        }
        if(mealsDao.deleteMealById(mealId) != 1){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Meal not found");
        }
    }
}


