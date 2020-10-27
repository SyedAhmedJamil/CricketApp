package com.example.cricketapp.tabs_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cricketapp.LiveMatch;
import com.example.cricketapp.R;
import com.example.cricketapp.databinding.FragmentLiveTabBinding;
import com.example.cricketapp.main_fragmanets.livematch.LiveMatchViewModel;

public class LiveTabFragment extends Fragment {

    LiveMatchViewModel viewModel;
    boolean isFirstLaunch = true;
    TextToSpeech textToSpeech;
    public LiveTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireParentFragment()).get(LiveMatchViewModel.class);
        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentLiveTabBinding binding = FragmentLiveTabBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(viewModel);
        viewModel.liveMatch.observe(getViewLifecycleOwner(), new Observer<LiveMatch>() {
            @Override
            public void onChanged(LiveMatch liveMatch) {
                if(!liveMatch.playerDataList.get(0).getPlayerName().equals(viewModel.currentBaller))
                {
                    binding.overScores.removeAllViews();
                    viewModel.currentBaller = liveMatch.playerDataList.get(0).getPlayerName();
                }
                String[] recents = liveMatch.matchData.getRecent().split("\\|");
                if(isFirstLaunch)
                {
                    for(int i=1; i<recents.length; i++)
                    {
                        TextView textView = (TextView)LayoutInflater.from(getContext()).inflate(R.layout.ball_result,binding.overScores,false);
                        textView.setText(recents[i].trim());
                        binding.overScores.addView(textView);
                    }
                    isFirstLaunch = false;
                }
                else {
                    textToSpeech.speak("BALL",TextToSpeech.QUEUE_ADD,null,null);
                    binding.textViewBall.setText("BALL");
                    binding.textViewBall.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.textViewBall.setText(recents[recents.length - 1].trim());
                            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.ball_result, binding.overScores,false);
                            textView.setText(recents[recents.length - 1].trim());
                            binding.overScores.addView(textView);
                        }
                    },3000);

                }
            }
        });


        return  binding.getRoot();
    }
}