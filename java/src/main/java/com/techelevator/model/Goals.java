package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;

public class Goals {
    private double currentWeight;

    private double desiredWeight;

    private double bmi;

    private LocalDate date;

    public Goals(double currentWeight, double desiredWeight, double bmi, LocalDate date) {
        this.currentWeight = currentWeight;
        this.desiredWeight = desiredWeight;
        this.bmi = bmi;
        this.date = date;
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

