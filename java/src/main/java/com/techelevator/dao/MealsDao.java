package com.techelevator.dao;

import com.techelevator.model.Meals;

import java.util.List;

public interface MealsDao {
    List<Meals> findAll();
    Meals findMealById(int mealId);
    Meals createMeal(Meals meals);
    Meals getMealById(int mealId);
    int deleteMealById(int mealId);
    Meals updateMeal(Meals meals);
}
