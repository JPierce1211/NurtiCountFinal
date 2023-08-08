package com.techelevator.dao;

import com.techelevator.model.Meals;

import java.util.List;

public interface MealsDao{
    List<Meals> findAllById();
    Meals createMeal(Meals meals);
    Meals getMealByProfile(int profileId);
    int deleteMealById(int mealId);
    Meals updateMealsById(Meals meals);
}
