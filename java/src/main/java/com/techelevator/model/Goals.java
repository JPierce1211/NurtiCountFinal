package com.techelevator.model;

public class Goals {

    private int goalId;
    private int userId;
    private double desiredWeight;
    private double bmi;
    private String logDay;

    public Goals(){};

    public Goals(int goalId, int userId, double desiredWeight, double bmi, String logDay) {
        this.goalId = goalId;
        this.userId = userId;
        this.desiredWeight = desiredWeight;
        this.bmi = bmi;
        this.logDay = logDay;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getLogDay() {
        return logDay;
    }

    public void setLogDay(String logDay) {
        this.logDay = logDay;
    }
}
