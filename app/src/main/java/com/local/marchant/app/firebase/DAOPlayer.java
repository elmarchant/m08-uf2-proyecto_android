package com.local.marchant.app.firebase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPlayer {
    private DatabaseReference databaseReference;

    public DAOPlayer() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Player.class.getSimpleName());
    }

    public Task<Void> add(Player player){
        return databaseReference.push().setValue(player);
    }
}
