package com.example.cricketapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreboardTabFragment extends Fragment {

    LinearLayout yay_layout;

    public ScoreboardTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scoreboard_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        yay_layout = view.findViewById(R.id.yay_layout);
        ImageView textView = view.findViewById(R.id.sr);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yay_layout.getVisibility() == View.VISIBLE)
                    yay_layout.setVisibility(View.GONE);
                else
                    yay_layout.setVisibility(View.VISIBLE);
            }
        });

    }

}