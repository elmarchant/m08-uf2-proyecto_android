package com.local.marchant.app.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.local.marchant.app.R;
import com.local.marchant.app.firebase.DAOPlayer;
import com.local.marchant.app.firebase.Player;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button nav_play, nav_scores;
    private EditText edittext_pn;

    public StartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartFragment newInstance(String param1, String param2) {
        StartFragment fragment = new StartFragment();
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_start, container, false);

        getActivity().setTitle("Simon Game | Start");

        nav_play = (Button) root.findViewById(R.id.nav_play);
        edittext_pn = (EditText) root.findViewById(R.id.editTextTextPlayerName);
        nav_scores = (Button) root.findViewById(R.id.nav_scores);
        DAOPlayer daoP = new DAOPlayer();

        nav_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edittext_pn.getText().toString().trim();

                if(!username.equals("") && !username.contains(" ")){
                    Player p = new Player(username,0);
                    daoP.add(p).addOnSuccessListener(suc->{
                        Toast.makeText(getContext(),"Jugador insertado con éxito",Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(er->{
                        Toast.makeText(getContext(),""+er.getMessage(),Toast.LENGTH_SHORT).show();
                    });

                    Navigation.findNavController(root).navigate(R.id.fromStartToPlay);
                }else{
                    Toast.makeText(getContext(),"Nombre de usuario no válido",Toast.LENGTH_SHORT).show();
                }
            }
        });

        nav_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.fromStartToScores);
            }
        });

        return root;
    }
}