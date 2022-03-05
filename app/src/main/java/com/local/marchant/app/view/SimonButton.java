package com.local.marchant.app.view;

import android.content.Context;
import android.media.MediaPlayer;

public class SimonButton {
    private int color, activeColor;
    private float top, left, bottom, right;
    private boolean active = false;
    private MediaPlayer audio;

    public SimonButton(int color, int active_color, Context context, int audio_id){
        this.color = color;
        this.activeColor = active_color;
        top = 0;
        left = 0;
        bottom = 0;
        right = 0;
        audio = MediaPlayer.create(context, audio_id);
    }

    public int getColor() {
        return color;
    }

    public void setActive(boolean active){
        if(active){
            if(audio.isPlaying()){
                audio.pause();
                audio.seekTo(0);
                audio.start();
            }else{
                if(audio.getCurrentPosition() == audio.getDuration()){
                    audio.seekTo(0);
                }

                audio.start();
            }
        }

        this.active = active;
    }

    public MediaPlayer getAudio(){
        return audio;
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
