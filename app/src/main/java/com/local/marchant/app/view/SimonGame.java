package com.local.marchant.app.view;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class SimonGame {
    private CanvasView canvasView;
    private int level = 1, maxScore = 0, score = 0, userIndex = 0;
    private ArrayList<Integer> repeats = new ArrayList<Integer>();
    private String username;
    private boolean status = true;
    private boolean turn = false;

    public SimonGame(CanvasView canvasView, String username){
        this.username = username;
        this.canvasView = canvasView;
    }

    public void play(){
        int adder = 1, counter = 0;
        boolean last = false;
        addRepeat();
        userIndex = 0;
        Handler handler = new Handler();

        for (int index : repeats) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < canvasView.buttons.length; i++) {
                        if(i != index){
                            if(canvasView.buttons[i].getAudio().isPlaying()){
                                canvasView.buttons[i].getAudio().pause();
                                canvasView.buttons[i].getAudio().seekTo(0);
                            }
                        }
                    }

                    canvasView.buttons[index].setActive(true);
                    Log.i("Game", "Activating button");
                    canvasView.invalidate();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            canvasView.buttons[index].setActive(false);
                            Log.i("Game", "Desactivating button");
                            canvasView.invalidate();
                        }
                    }, 250);
                }
            }, 500 * adder);

            if(counter == (repeats.size() - 1)){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        turn = true;
                    }
                }, 500 * adder + 250);
            }

            counter++;
            adder++;
        }
    }

    private void addRepeat(){
        repeats.add(getRandomInt(0, 4));
    }

    public boolean userAttempt(int number){
        if(number == repeats.get(userIndex)){
            userIndex++;
            score++;

            if(score > maxScore) maxScore = score;
            return true;
        }else{
            repeats.clear();
            score = 0;
            return false;
        }
    }

    private int getRandomInt(int min, int max){
        Random random = new Random();

        return random.nextInt(max - min) + min;
    }

    public boolean getStatus() {
        return status;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public int getScore() {
        return score;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void end() {
        this.status = false;
    }

    public boolean userTurn(){
        return turn;
    }

    public void setTurn(boolean turn){
        this.turn = turn;
    }

    public boolean isTail(){
        if(userIndex == repeats.size()) return true;
        else return false;
    }

    /*public void resetUserIndex(){
        userIndex = 0;
    }*/
}
