package com.local.marchant.app.ui;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.local.marchant.app.R;
import com.local.marchant.app.view.CanvasView;
import com.local.marchant.app.view.SimonGame;

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
    private Button nav_exit, start_game;
    private SimonGame game;
    private CanvasView cview;
    private boolean clicking = false;
    private int index = 0;
    private Button btn_soundtrack;
    private int posicion = 0;
    private MediaPlayer soundtrack;
    private TextView maxScore, score;

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

        btn_soundtrack = (Button) root.findViewById(R.id.btn_soundtrack);
        nav_exit = (Button) root.findViewById(R.id.nav_exit);
        start_game = (Button) root.findViewById(R.id.start_game);
        cview = (CanvasView) root.findViewById(R.id.canvas);

        score = (TextView) root.findViewById(R.id.score);
        maxScore = (TextView) root.findViewById(R.id.max_score);

        score.setText("0");
        maxScore.setText("0");

        nav_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundtrack.stop();
                Navigation.findNavController(root).navigate(R.id.fromPlayToStart);
            }
        });

        soundtrack = MediaPlayer.create(getContext(),R.raw.soudtrack);
        soundtrack.setLooping(true);
        soundtrack.start();

        btn_soundtrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundtrack != null && soundtrack.isPlaying()){
                    posicion = soundtrack.getCurrentPosition();
                    soundtrack.pause();
                }else if(soundtrack != null && soundtrack.isPlaying() == false) {
                    soundtrack.seekTo(posicion);
                    soundtrack.start();
                }
            }
        });

        cview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(cview.isPlaying() && !clicking){
                    if(game.userTurn()){
                        if(event.getActionMasked() == MotionEvent.ACTION_UP){

                            cview.buttons[index].setActive(false);

                            boolean result = game.userAttempt(index);

                            score.setText(String.valueOf(game.getScore()));
                            maxScore.setText(String.valueOf(game.getMaxScore()));

                            if(!result || game.isTail()){
                                game.setTurn(false);
                                game.play();
                            }

                            Log.i("Action", "Touch up");
                        }else if(event.getAction() == MotionEvent.ACTION_DOWN){
                            float x = event.getX(), y = event.getY();
                            int X = (int) x / (cview.getWidth() / 2), Y = (int) y / (cview.getHeight() / 2);
                            String color = "";

                            if(X == 0 && Y == 0){
                                color = "GREEN";
                                index = 0;
                            }else if(X == 1 && Y == 0){
                                color = "RED";
                                index = 1;
                            } else if(X == 0 && Y == 1) {
                                color = "YELLOW";
                                index = 2;
                            } else if(X == 1 && Y == 1) {
                                color = "BLUE";
                                index = 3;
                            }

                            for (int i = 0; i < cview.buttons.length; i++){
                                if(i != index){
                                    if(cview.buttons[i].getAudio().isPlaying()){
                                        cview.buttons[i].getAudio().pause();
                                        cview.buttons[i].getAudio().seekTo(0);
                                    }
                                }
                            }

                            cview.buttons[index].setActive(true);

                            Log.i("TAG", "Touched color: " + color);
                            Log.i("Action", "Touch down");
                        }

                        cview.invalidate();
                    }
                }

                return true;
            }
        });

        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CanvasView canvasView = (CanvasView) root.findViewById(R.id.canvas);

                game = new SimonGame(cview, "user");
                game.play();
                cview.setPlaying(true);
                start_game.setText("Reset");
            }
        });

        return root;
    }
}