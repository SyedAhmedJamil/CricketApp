package com.example.cricketapp.main_fragmanets.LiveMatch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cricketapp.CricketService;
import com.example.cricketapp.MatchDetailsList;
import com.example.cricketapp.MatchDetailsListAdapter;
import com.example.cricketapp.MatchSummary;
import com.example.cricketapp.R;
import com.example.cricketapp.UnsafeOkHttpClient;
import com.example.cricketapp.tabs_fragments.InfoTabFragment;
import com.example.cricketapp.tabs_fragments.LiveTabFragment;
import com.example.cricketapp.tabs_fragments.ScoreboardTabFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveMatchFragment extends Fragment {

    MatchDetailsListAdapter adapter;
    ViewPager2 viewPager2;
    ViewPager2Adapter viewPager2Adapter;
    TabLayout tabLayout;
    MatchSummary matchSummary;
    TextView textView;
    LiveMatchViewModel viewModel;
    public LiveMatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_match, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager2 = view.findViewById(R.id.pager);
        viewPager2Adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(viewPager2Adapter);
        viewPager2.setUserInputEnabled(false);
        tabLayout = view.findViewById(R.id.live_tabs);
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

        textView = view.findViewById(R.id.match_title);
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