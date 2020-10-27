package com.example.cricketapp.main_fragmanets.live;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.cricketapp.MainActivity;
import com.example.cricketapp.Match;
import com.example.cricketapp.MatchDetailsListAdapter;
import com.example.cricketapp.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class LiveFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    MatchDetailsListAdapter adapter;
    LiveViewModel liveViewModel;
    SwipeRefreshLayout swipeRefreshLayout;
    boolean isDataLoaded = false;
    public LiveFragment() {
     }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveViewModel = new ViewModelProvider(this).get(LiveViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        ProgressBar progressBar = view.findViewById(R.id.indeterminateBar);
        liveViewModel.refresh(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.match_details_recyclerview);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        MaterialToolbar topAppBar = view.findViewById(R.id.topAppBar);

        adapter = new MatchDetailsListAdapter(new ArrayList<>());
        adapter.setOnClickListener(new MatchDetailsListAdapter.OnClickListener() {
            @Override
            public void onClick(Integer matchId,String teamAName, String teamBName) {
                Bundle bundle = new Bundle();
                bundle.putString("match_id", matchId.toString());
                bundle.putString("team_a_name", teamAName);
                bundle.putString("team_b_name", teamBName);
                MainActivity.navController.navigate(R.id.action_fragment_live_to_liveMatchFragment4,bundle);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.fragment_live_refresh :
                    {
                        swipeRefreshLayout.setRefreshing(true);
                        liveViewModel.refresh(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        liveViewModel.getMatches().observe(getViewLifecycleOwner(), new Observer<List<Match>>() {
            @Override
            public void onChanged(List<Match> matches) {
                adapter.setMatchList(matches);
            }
        });
    }

    @Override
    public void onRefresh() {
        liveViewModel.refresh(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}