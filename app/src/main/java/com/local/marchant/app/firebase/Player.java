package com.local.marchant.app.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Player {
    private String user_name;
    private int maxscore;

    public Player(String user_name,int maxscore) {
        this.user_name = user_name;
        this.maxscore = maxscore;
    }

    public int getMaxscore() {
        return maxscore;
    }

    public void setMaxscore(int maxscore) {
        this.maxscore = maxscore;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Map<String, Object> getMap(){
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("user_name", user_name);
        map.put("maxscore", maxscore);

        return map;
    }
}
