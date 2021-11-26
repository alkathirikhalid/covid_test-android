package com.iquii.covidtest.model.entity;

public class DataPoint {

    private int x;
    private int y;

    public DataPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
