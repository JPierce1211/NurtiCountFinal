package com.techelevator.dao;

import com.techelevator.model.Meals;

import java.util.List;

public interface MealsDao {
    List<Meals> findAll();
    Meals createMeal(Meals meals);
    Meals getMealById(int mealId);
    int deleteMealById(int mealId);
    Meals updateMeals(Meals meals);
}
