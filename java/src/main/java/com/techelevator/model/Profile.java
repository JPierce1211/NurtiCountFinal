package com.techelevator.model;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Date;

public class Profile {

    private int userId;
    private int profileId;

    private String birthday;

    private double height; //Most fitness and health app measure height in meters

    private double currentWeight; //Will display as pounds

    private String displayName;

    private int profilePicId;

    private int goals;

    public Profile() {};

    public Profile(int userId, int profileId, double height, String displayName, String birthday, double startingWeight, int profilePicId, int goals){
        this.userId = userId;
        this.profileId = profileId;
        this.height = height;
        this.displayName = displayName;
        this.birthday = birthday;
        this.currentWeight = startingWeight;
        this.profilePicId = profilePicId;
        this.goals = goals;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getProfilePicId() {
        return profilePicId;
    }

    public void setProfilePicId(int profilePicId) {
        this.profilePicId = profilePicId;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }
}
