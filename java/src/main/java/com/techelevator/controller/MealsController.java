package com.techelevator.controller;

import com.techelevator.dao.JdbcMealsDao;
import com.techelevator.model.Meals;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MealsController {
    private JdbcTemplate jdbcTemplate;
    private JdbcMealsDao mealsDao;

    public MealsController(JdbcMealsDao mealsDao){
        this.mealsDao = mealsDao;
    }

    @GetMapping("/meals")
    public List<Meals> listMeals()
}
