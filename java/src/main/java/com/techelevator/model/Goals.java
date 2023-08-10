package com.techelevator.model;

public class Goals {

    private int goalId;
    private double desiredWeight;
    private double bmi;
    private String date;

    public Goals(){};

    public Goals(int goalId, double desiredWeight, double bmi, String date) {
        this.goalId = goalId;
        this.desiredWeight = desiredWeight;
        this.bmi = bmi;
        this.date = date;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
