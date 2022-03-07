package com.local.marchant.app.firebase;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class DAOPlayer {
    private DatabaseReference databaseReference;

    public DAOPlayer() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Player.class.getSimpleName());
    }

    public void update(Player player){
        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();

        fb.child("Player").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String username = "";

                    for(DataSnapshot child : snapshot.getChildren()){
                        username = snapshot.child(child.getKey() + "/user_name").getValue().toString();

                        if(player.getUser_name().equals(username)){
                            fb.child("Player/" + child.getKey()).updateChildren(player.getMap());
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void add(Player player){

        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();

        fb.child("Player").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String username = "";
                    boolean exists = false;

                    for(DataSnapshot child : snapshot.getChildren()){
                        username = snapshot.child(child.getKey() + "/user_name").getValue().toString();

                        if(player.getUser_name().equals(username)){
                            exists = true;
                            break;
                        }
                    }

                    if(!exists) databaseReference.push().setValue(player);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
