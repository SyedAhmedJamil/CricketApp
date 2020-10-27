package com.example.cricketapp.main_fragmanets.livematch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
    FragmentLiveMatchBinding binding;
    public LiveMatchFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LiveMatchViewModel.class);
        viewModel.matchId = getArguments().getString("match_id");
        viewModel.teamAName = getArguments().getString("team_a_name");
        viewModel.teamBName = getArguments().getString("team_b_name");
        viewModel.getUpdate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLiveMatchBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(viewModel);

        TabLayout tabLayout = binding.liveTabs;
        ViewPager2 viewPager = binding.pager;
        ProgressBar progressBar = binding.indeterminateBar;

        viewPager.setVisibility(View.INVISIBLE);
        viewPager.setUserInputEnabled(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new ViewPager2Adapter(this));

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout,viewPager,false,true,(tab, position) -> {
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
        });
        tabLayoutMediator.attach();

        viewModel.setCallback(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
            }
        });

        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.pager.getVisibility() == View.VISIBLE)
                    binding.pager.setVisibility(View.INVISIBLE);
                else
                    binding.pager.setVisibility(View.VISIBLE);
            }
        });

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
                    returnFragment = new LiveTabFragment();
                }
                break;
                case 1:{
                    returnFragment = new ScoreboardTabFragment();
                }
                break;
                case 2:{
                    returnFragment = new InfoTabFragment();
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