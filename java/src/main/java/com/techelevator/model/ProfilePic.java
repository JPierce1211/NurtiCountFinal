package com.techelevator.model;

public class ProfilePic {

    private int profilePicId;

    private String url;

    public ProfilePic(){}

    public ProfilePic(int profilePicId, String url) {
        this.profilePicId = profilePicId;
        this.url = url;
    }

    public int getProfilePicId() {
        return profilePicId;
    }

    public void setProfilePicId(int profilePicId) {
        this.profilePicId = profilePicId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
