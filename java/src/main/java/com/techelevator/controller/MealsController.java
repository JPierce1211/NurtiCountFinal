package com.techelevator.controller;

import com.techelevator.dao.JdbcMealsDao;
import com.techelevator.dao.JdbcProfileDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.ProfileDao;
import com.techelevator.exception.DaoException;
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
@PreAuthorize("isAuthenticated()")
@RestController
public class MealsController {
    private JdbcTemplate jdbcTemplate;
    private JdbcMealsDao mealsDao;
    private ProfileDao profileDao;
    private JdbcUserDao userDao;
    public MealsController(JdbcMealsDao mealsDao, JdbcProfileDao profileDao, JdbcUserDao userDao){
        this.mealsDao = mealsDao;
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping("/meals")
    public List<Meals> listMeals(@PathVariable int profileId, Principal principal){
        //Profile profile = profileDao.getProfileById(profileId);
        User user = userDao.getUserByUsername(principal.getName());
        if(user != null){
            return mealsDao.findAll(user.getId());
        }else{
        }
            return null;
    }
    @GetMapping("/meals/{id}")
    public Meals get(@PathVariable int profileId, @PathVariable int mealsId, Principal principal) {
        //Profile profile = profileDao.getProfileById(profileId);
        User user = userDao.getUserByUsername(principal.getName());

        if (user != null) {
            Meals meals = mealsDao.getMealById(mealsId);
            if (meals != null) {
                return meals;
            } else {
                return null;
            }
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createMeal")
    public Meals createMeal(@PathVariable int profileId, @RequestBody Meals meals, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        if(user != null){
            Profile profile = profileDao.getProfileById(user.getId());
            if(profile != null){
                meals.setProfileId(profile.getProfileId());
                return mealsDao.createMeal(meals);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/meals/{id}")
    public Meals update(Principal principal, @RequestBody Meals updatedMeal, @PathVariable int mealId, Profile profile){
        //Profile profile = profileDao.getProfileById(profileId);
        User user = userDao.getUserByUsername(principal.getName());
        if(user != null){
             profile = profileDao.getProfileById(user.getId());
            if(profile != null){
            Meals updatedMeals = mealsDao.getMealById(mealId);
            if(updatedMeals != null) {
                if(updatedMeal.getProfileId(profile.getProfileId()) == profile.getUserId());
            }
                updatedMeal.setProfileId(profile.getProfileId());
                updatedMeal.setMealId(mealId);

                try {
                    Meals updateMeal = mealsDao.updateMealsById(updatedMeal);
                    return updateMeal;
                } catch (DaoException e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update meal");
                }
//                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found");
                }
            }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
               return updatedMeal;
            }

    //meals.setMealId(mealId);
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/meals/{id}")
    public void delete(@PathVariable int mealId, @PathVariable int profileId, Principal principal){
        Profile profile = profileDao.getProfileById(profileId);
        if(profile != null){
            int deleteMeal = mealsDao.deleteMealById(mealId);
        }
        if(mealsDao.deleteMealById(mealId) != 1){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Meal not found");
        }
    }
}


