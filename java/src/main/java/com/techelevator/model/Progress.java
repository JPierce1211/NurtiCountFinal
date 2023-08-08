package com.techelevator.model;

import java.time.LocalDate;

public class Progress {

    private int progressId;
    private int profileId;
    private double currentWeight;

    private double desiredWeight;

    private double bmi;

    private LocalDate date;

    public Progress (){};

    public Progress(int progressId, int profileId, double currentWeight, double desiredWeight, double bmi, LocalDate date) {
        this.progressId = progressId;
        this.profileId = profileId;
        this.currentWeight = currentWeight;
        this.desiredWeight = desiredWeight;
        this.bmi = bmi;
        this.date = date;
    }

    public int getProgressId() {
        return progressId;
    }

    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getDesiredWeight() {
        return desiredWeight;
    }

    public void setDesiredWeight(double desiredWeight) {
        this.desiredWeight = desiredWeight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
