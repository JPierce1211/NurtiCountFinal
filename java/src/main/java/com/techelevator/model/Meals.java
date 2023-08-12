package com.techelevator.model;

import java.time.LocalDate;

public class Meals {

    private int mealId;
    private int userId;

    private String mealType;

    private String logDay;

    private boolean isQuickMeal;

    public Meals() {};

    public Meals(int mealId, int userId, String mealType, String logDay, boolean isQuickMeal) {
        this.mealId = mealId;
        this.userId = userId;
        this.mealType = mealType;
        this.logDay = logDay;
        this.isQuickMeal = isQuickMeal;

    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getLogDay() {
        return logDay;
    }

    public void setLogDay(String logDay) {
        this.logDay = logDay;
    }

    public boolean isQuickMeal() {
        return isQuickMeal;
    }

    public void setQuickMeal(boolean quickMeal) {
        isQuickMeal = quickMeal;
    }

    public void addNewFood(Food food) {
    }

}
