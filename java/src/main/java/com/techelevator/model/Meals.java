package com.techelevator.model;

import java.time.LocalDate;

public class Meals {

    private int mealId;
    private int userId;

    private String mealType;

    private LocalDate mealDate;

    public Meals() {};

    public Meals(int mealId, String mealType, LocalDate mealDate, int userId) {
        this.mealId = mealId;
        this.mealType = mealType;
        this.mealDate = mealDate;
        this.userId = userId;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getMealType(String mealType) {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public LocalDate getMealDate(LocalDate mealDate) {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    public int getUserId(int userId) {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
