package com.local.marchant.app.ui;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.local.marchant.app.R;
import com.local.marchant.app.view.CanvasView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Canvas canvas;
    private RelativeLayout screen;
    private Paint paint;

    // Mediaplay audio de botones
    private MediaPlayer song_green;
    private MediaPlayer song_blue;
    private MediaPlayer song_yellow;
    private MediaPlayer song_red;



    public PlayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayFragment newInstance(String param1, String param2) {
        PlayFragment fragment = new PlayFragment();
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_play, container, false);

        getActivity().setTitle("Simon Game | Play");
      
        View canvasV = (CanvasView) root.findViewById(R.id.canvas);
        song_green = MediaPlayer.create(getContext(), R.raw.b3);
        song_blue = MediaPlayer.create(getContext(), R.raw.e2);
        song_yellow = MediaPlayer.create(getContext(), R.raw.d3);
        song_red = MediaPlayer.create(getContext(), R.raw.g3);

        canvasV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX(), y = event.getY();
                int X = (int) x / (canvasV.getWidth() / 2), Y = (int) y / (canvasV.getHeight() / 2);
                String color = "";

                if(X == 0 && Y == 0) {
                    song_green.start();
                    color = "GREEN";
                }
                else if(X == 1 && Y == 0){
                    song_red.start();
                    color = "RED";
                }
                else if(X == 0 && Y == 1){
                    song_yellow.start();
                    color = "YELLOW";
                }
                else if(X == 1 && Y == 1){
                    song_blue.start();
                    color = "BLUE";
                }

                Log.i("TAG", "Touched color: " + color);

                return false;
            }
        });
        return root;
    }
}