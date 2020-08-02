package com.example.mynews.models;

public class Calification {

    private int calification;

    public Calification(int calification) {
        this.calification = calification;
    }

    public int getCalification() {
        return calification;
    }

    public void setCalification(int calification) {
        this.calification = calification;
    }

    @Override
    public String toString() {
        return "Calification{" +
                "calification=" + calification +
                '}';
    }
}

