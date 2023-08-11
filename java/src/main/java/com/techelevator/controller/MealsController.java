package com.techelevator.controller;

import com.techelevator.dao.JdbcMealsDao;
import com.techelevator.dao.JdbcProfileDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.ProfileDao;
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
    public List<Meals> listMeals(Principal principal){
        //Profile profile = profileDao.getProfileById(profileId);
        User user = userDao.getUserByUsername(principal.getName());
        if(user != null){
            return mealsDao.findAll(user.getId());
        }else{
        }
            return null;
    }
    @GetMapping("/meals/{mealId}")
    public Meals get(@PathVariable int mealId, Principal principal) {
        //Profile profile = profileDao.getProfileById(profileId);
        User user = userDao.getUserByUsername(principal.getName());

        if (user != null) {
            Meals meals = mealsDao.getMealById(mealId);
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
    public Meals createMeal(@RequestBody Meals meals, @RequestBody Food food, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        if(user != null){
//            Profile profile = profileDao.getProfileById(user.getId());
//            if(profile != null){
//                meals.setProfileId(profile.getProfileId());
                return mealsDao.createMeal(meals, (List<Food>) food);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
//        }else{
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }

    }

    @PutMapping("/{mealId}")
    public Meals update(Principal principal, @RequestBody Meals updatedMeal, @PathVariable int mealId) {
//        User user = userDao.getUserByUsername(principal.getName());
//        if(user != null){
//            Meals currentMeal = mealsDao.getMealById(mealId);
//            if(currentMeal != null){
//                updatedMeal.g
//            }
//        }
        Meals newMeal = mealsDao.updateMealsById(updatedMeal, mealId);
        return newMeal;
    }

    //meals.setMealId(mealId);
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/mealsId}")
    public void delete(@PathVariable int mealId, Principal principal){
        //Profile profile = profileDao.getProfileById(profileId);
        User user = userDao.getUserByUsername(principal.getName());
        if(user != null){
            int deleteMeal = mealsDao.deleteMealById(mealId);
        }
        if(mealsDao.deleteMealById(mealId) != 1){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Meal not found");
        }
    }
}


