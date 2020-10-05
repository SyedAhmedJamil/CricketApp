package com.example.cricketapp.main_fragmanets.livematch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cricketapp.LiveMatch;
import com.example.cricketapp.MatchDetailsListAdapter;
import com.example.cricketapp.R;
import com.example.cricketapp.databinding.FragmentLiveMatchBinding;
import com.example.cricketapp.tabs_fragments.InfoTabFragment;
import com.example.cricketapp.tabs_fragments.LiveTabFragment;
import com.example.cricketapp.tabs_fragments.ScoreboardTabFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LiveMatchFragment extends Fragment {

    LiveMatchViewModel viewModel;
    String match_id;
    public LiveMatchFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        match_id = getArguments().getString("match_id");
        viewModel = new ViewModelProvider(this).get(LiveMatchViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentLiveMatchBinding binding = FragmentLiveMatchBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(viewModel);
        binding.setMatchId(match_id);

        ViewPager2 viewPager2 = binding.pager;
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(viewPager2Adapter);
        viewPager2.setUserInputEnabled(false);
        TabLayout tabLayout = binding.liveTabs;
        new TabLayoutMediator(tabLayout,viewPager2,false,true,(tab, position) -> {
            switch (position)
            {
                case 0:
                    tab.setText("LIVE");
                    break;
                case 1:
                    tab.setText("SCOREBOARD");
                    break;
                case 2:
                    tab.setText("INFO");
                    break;

            }
        }).attach();

        return binding.getRoot();
    }

    private class ViewPager2Adapter extends FragmentStateAdapter {
        public ViewPager2Adapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment returnFragment = null;
            switch (position)
            {
                case 0: {
                    Fragment fragment = new LiveTabFragment();
                    returnFragment = fragment;
                }
                break;
                case 1:{
                    Fragment fragment = new ScoreboardTabFragment();
                    returnFragment = fragment;
                }
                break;
                case 2:{
                    Fragment fragment = new InfoTabFragment();
                    returnFragment = fragment;
                }
            }
            return returnFragment;

        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}