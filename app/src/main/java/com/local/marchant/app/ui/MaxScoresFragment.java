package com.local.marchant.app.ui;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.local.marchant.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MaxScoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaxScoresFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button nav_start;
    private LinearLayout dataLayout;
    private TextView Tname, Tscores;
    private DatabaseReference fb;

    public MaxScoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MaxScoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MaxScoresFragment newInstance(String param1, String param2) {
        MaxScoresFragment fragment = new MaxScoresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_max_scores, container, false);

        nav_start = (Button) root.findViewById(R.id.nav_go_back);
        dataLayout = (LinearLayout) root.findViewById(R.id.dataLayout);
        Tname = (TextView) root.findViewById(R.id.hs_name);
        Tscores = (TextView) root.findViewById(R.id.hs_scores);

        fb = FirebaseDatabase.getInstance().getReference();

        fb.child("Player").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    LinearLayout row, parent;
                    TextView name, score;

                    for(DataSnapshot child : snapshot.getChildren()){
                        row = new LinearLayout(getContext());
                        row.setOrientation(LinearLayout.HORIZONTAL);

                        dataLayout.addView(row);

                        row.setPadding(32, 32, 32, 32);

                        name = new TextView(getContext());
                        score = new TextView(getContext());

                        name.setText(snapshot.child(child.getKey() + "/user_name").getValue().toString());
                        score.setText(snapshot.child(child.getKey() + "/maxscore").getValue().toString());

                        name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        score.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        name.setLayoutParams(Tname.getLayoutParams());
                        score.setLayoutParams(Tscores.getLayoutParams());

                        row.addView(name);
                        row.addView(score);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        nav_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.fromScoresToStart);
            }
        });

        return root;
    }
}