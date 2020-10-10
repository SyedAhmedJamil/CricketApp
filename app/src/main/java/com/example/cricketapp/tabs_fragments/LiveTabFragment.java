package com.example.cricketapp.tabs_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cricketapp.LiveMatch;
import com.example.cricketapp.R;
import com.example.cricketapp.databinding.FragmentLiveTabBinding;
import com.example.cricketapp.main_fragmanets.livematch.LiveMatchViewModel;

public class LiveTabFragment extends Fragment {

    LiveMatchViewModel viewModel;
    public LiveTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireParentFragment()).get(LiveMatchViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentLiveTabBinding binding = FragmentLiveTabBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(viewModel);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel.getUpdate().observe(getViewLifecycleOwner(), new Observer<LiveMatch>() {
            @Override
            public void onChanged(LiveMatch liveMatch) {
                String[] list = liveMatch.matchData.getRecent().split(" \\| ",7);
            }
        });
    }
}