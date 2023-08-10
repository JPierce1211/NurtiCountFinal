package com.techelevator.model;

import java.time.LocalDate;

public class Meals {

    private int mealId;
    private int profileId;

    private String mealType;

    private String mealDate;

    public Meals() {};

    public Meals(int mealId, String mealType, String mealDate, int profileId) {
        this.mealId = mealId;
        this.mealType = mealType;
        this.mealDate = mealDate;
        this.profileId = profileId;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
        this.profileId = profileId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealDate() {
        return mealDate;
    }

    public void setMealDate(String mealDate) {
        this.mealDate = mealDate;
    }

    public int getProfileId(int profileId) {
        return this.profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
}
