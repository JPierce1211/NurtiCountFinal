package com.techelevator.model;

public class FoodMealDto {
    private int mealId;
    private int foodId;
    private String logDay;
    private String foodName;
    private double calories;
    private int servingSize;
    private int numOfServings;
    private boolean quickFood;

    public FoodMealDto(){};
    public FoodMealDto(int mealId, int foodId, String logDay, String foodName, double calories, int servingSize, int numOfServings, boolean quickFood) {
        this.mealId = mealId;
        this.foodId = foodId;
        this.logDay = logDay;
        this.foodName = foodName;
        this.calories = calories;
        this.servingSize = servingSize;
        this.numOfServings = numOfServings;
        this.quickFood = quickFood;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getLogDay() {
        return logDay;
    }

    public void setLogDay(String logDay) {
        this.logDay = logDay;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public int getNumOfServings() {
        return numOfServings;
    }

    public void setNumOfServings(int numOfServings) {
        this.numOfServings = numOfServings;
    }

    public boolean getQuickFood() {
        return quickFood;
    }

    public void setQuickFood(boolean quickFood) {
        this.quickFood = quickFood;
    }
}
