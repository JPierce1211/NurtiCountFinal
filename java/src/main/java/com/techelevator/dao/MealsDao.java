package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.Meals;

import java.time.LocalDate;
import java.util.List;

public interface MealsDao{
    List<Meals> findAll(int userId);
    Meals createMeal(Meals meals, List<Food> foodList);
    Meals getMealById(int mealId);
    int deleteMealById(int mealId);
    Meals updateMealsById(Meals meals, int id);
    Meals getMealDate(String date);
}
