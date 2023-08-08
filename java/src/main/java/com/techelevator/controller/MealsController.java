package com.techelevator.controller;

import com.techelevator.dao.JdbcMealsDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Meals;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MealsController {
    private JdbcTemplate jdbcTemplate;
    private JdbcMealsDao mealsDao;

    public MealsController(JdbcMealsDao mealsDao){
        this.mealsDao = mealsDao;
    }

    @GetMapping("/meals")
    public List<Meals> listMeals(){
       return mealsDao.findAll();
    }
    @GetMapping("/meals/{id}")
    public Meals get(@PathVariable int mealsId){
        Meals meals = mealsDao.getMealById(mealsId);
        return meals;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-meal")
    public Meals createMeal(@RequestBody Meals meals){
        return mealsDao.createMeal(meals);
    }

    @PutMapping("/meals/{id}")
    public Meals update(@RequestBody Meals meals, @PathVariable int id){
        meals.setMealId(id);
        try{
            Meals updateMeal = mealsDao.updateMeals(meals);
            return updateMeal;
        }catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found.");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/meals/{id}")
    public void delete(@PathVariable int id){
        int deleteMeal = mealsDao.deleteMealById(id);
    }
}


