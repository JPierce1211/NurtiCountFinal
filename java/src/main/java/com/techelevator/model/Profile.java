package com.techelevator.model;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Date;

public class Profile {

    private int profileId;

    private double height; //Most fitness and health app measure height in meters

    private String displayName;

    private LocalDate birthday;

    private String profilePicture; //URL string???

    private double startingWeight; //Will display as pounds

    public Profile() {};

    public Profile(int profileId, double height, String displayName, LocalDate birthday, String profilePicture, double startingWeight){
        this.profileId = profileId;
        this.height = height;
        this.displayName = displayName;
        this.birthday = birthday;
        this.profilePicture = profilePicture;
        this.startingWeight = startingWeight;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public double getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }
}
