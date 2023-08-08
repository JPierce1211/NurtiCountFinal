package com.techelevator.controller;

import com.techelevator.dao.JdbcMealsDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Meals;
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

    @PostMapping("/create-meal")
    public Meals createMeal(@RequestBody Meals meals){
        return mealsDao.createMeal(meals);
    }
}


