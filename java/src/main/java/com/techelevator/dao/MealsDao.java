package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.Meals;

import java.time.LocalDate;
import java.util.List;

public interface MealsDao{
    List<Meals> findAll(int userId);
    Meals createMeal(Meals meals);
    int addFoodToMeal(int foodId, String logDay, int meal_id);
    // int getTotalCalories(int mealId);
    Meals getMealById(int userId);
    int deleteMealById(int mealId);
    Meals updateMealsById(Meals meals, int id);
    Meals getMealDate(String date);
}
