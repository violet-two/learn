package com.example.ui_control.bean;

public class Bubble {
    private float x;
    private float y;
    private int color;
    private float radius;

    public Bubble(float x, float y, int color, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
