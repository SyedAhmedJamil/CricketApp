package com.example.cricketapp.main_fragmanets;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cricketapp.CricketService;
import com.example.cricketapp.MatchDetailsList;
import com.example.cricketapp.UnsafeOkHttpClient;
import com.example.cricketapp.tabs_fragments.InfoTabFragment;
import com.example.cricketapp.tabs_fragments.LiveTabFragment;
import com.example.cricketapp.MatchDetailsListAdapter;
import com.example.cricketapp.R;
import com.example.cricketapp.tabs_fragments.ScoreboardTabFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveFragment extends Fragment {

    MatchDetailsListAdapter adapter;

    public LiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.match_details_recyclerview);
        adapter = new MatchDetailsListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        OkHttpClient unsafeOkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.crickssix.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(unsafeOkHttpClient)
                .build();
        CricketService cricketService = retrofit.create(CricketService.class);

        Call<MatchDetailsList> call = cricketService.getMatchDetailsList();
        call.enqueue(new Callback<MatchDetailsList>() {
            @Override
            public void onResponse(Call<MatchDetailsList> call, Response<MatchDetailsList> response) {
                MatchDetailsList matchDetailsList = response.body();
                adapter.setMatchDetailsList(matchDetailsList.matchDetailsList);
            }

            @Override
            public void onFailure(Call<MatchDetailsList> call, Throwable t) {
            }
        });

    }


}