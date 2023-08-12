package com.techelevator.model;

public class Food {
    private int foodId;

    private String foodName;

    private String foodType;

    private double servingSize;

    private double calories;

    private double numOfServings;

    public Food(int foodId, String foodName, String foodType, double servingSize, double calories, double numOfServings) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodType = foodType;
        this.servingSize = servingSize;
        this.calories = calories;
        this.numOfServings = numOfServings;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Food() {

    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }


    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public double getServingSize() {
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

    public double getNumOfServings() {
        return numOfServings;
    }

    public void setNumOfServings(int numOfServings) {
        this.numOfServings = numOfServings;
    }
}
