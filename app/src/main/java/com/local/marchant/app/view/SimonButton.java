package com.local.marchant.app.view;

public class SimonButton {
    private int color, activeColor;
    private float top, left, bottom, right;
    private boolean active = false;

    public SimonButton(int color, int active_color){
        this.color = color;
        this.activeColor = active_color;
        top = 0;
        left = 0;
        bottom = 0;
        right = 0;
    }

    public int getColor() {
        return color;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public int getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getTop() {
        return top;
    }

    public float getLeft() {
        return left;
    }

    public float getBottom() {
        return bottom;
    }

    public float getRight() {
        return right;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public void setRect(float top, float left, float bottom, float right){
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }
}
