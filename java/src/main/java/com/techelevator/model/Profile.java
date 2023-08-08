package com.techelevator.model;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Date;

public class Profile {

    private int userId;
    private int profileId;

    private LocalDate birthday;

    private double height; //Most fitness and health app measure height in meters

    private String gender;

    private double startingWeight; //Will display as pounds

    private String displayName;

    private int profilePicId;

    public Profile() {};

    public Profile(int userId, int profileId, double height, String displayName, LocalDate birthday, double startingWeight, int profilePicId){
        this.userId = userId;
        this.profileId = profileId;
        this.height = height;
        this.displayName = displayName;
        this.birthday = birthday;
        this.startingWeight = startingWeight;
        this.profilePicId = profilePicId;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public double getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }

    public int getProfilePicId() {
        return profilePicId;
    }

    public void setProfilePicId(int profilePicId) {
        this.profilePicId = profilePicId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
