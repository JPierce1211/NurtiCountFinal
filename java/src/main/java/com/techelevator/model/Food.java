package com.techelevator.model;

public class Food {
    private int foodId;

    private int mealId;

    private String foodType;

    private int servingSize;

    private double calories;

    private int numOfServings;

    public Food(int foodId, int mealId, String foodType, int servingSize, double calories, int numOfServings) {
        this.foodId = foodId;
        this.mealId = mealId;
        this.foodType = foodType;
        this.servingSize = servingSize;
        this.calories = calories;
        this.numOfServings = numOfServings;
    }

    public Food() {

    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public int getNumOfServings() {
        return numOfServings;
    }

    public void setNumOfServings(int numOfServings) {
        this.numOfServings = numOfServings;
    }
}
