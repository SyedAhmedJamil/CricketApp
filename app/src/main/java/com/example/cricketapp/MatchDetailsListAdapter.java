package com.example.cricketapp;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MatchDetailsListAdapter extends RecyclerView.Adapter<MatchDetailsListAdapter.MatchDetailsViewHolder> {

    private List<MatchDetails> matchDetailsList;
    private OnClickListener onClickListener;

    @NonNull
    @Override
    public MatchDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_details_recyclerview_item, parent, false);
        return new MatchDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchDetailsViewHolder holder, int position) {
        if (matchDetailsList != null) {
            MatchDetails matchDetails = matchDetailsList.get(position);
            String venue = matchDetails.getVenue().trim() + " on " + matchDetails.getStartDate() + " • " + matchDetails.getStartTime();
            holder.matchVenue.setText(venue);
            holder.teamAName.setText(matchDetails.getTeamAname().trim());
            holder.teamBName.setText(matchDetails.getTeamBname().trim());
            String matchTitle = matchDetails.getTeamAname() + " vs " + matchDetails.getTeamBname();
            holder.matchTitle.setText(matchTitle);
//            holder.matchDate.setText(matchDetails.getStartDate());
//            holder.matchTime.setText(matchDetails.getStartTime());
            holder.matchOvers.setText(matchDetails.getTotalover());

            holder.matchItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(matchDetails.getMainScreenMatchId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (matchDetailsList != null) {
            return matchDetailsList.size();
        }
        return 0;
    }

    public void setMatchDetailsList(List<MatchDetails> matchDetailsList) {
        this.matchDetailsList = matchDetailsList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    class MatchDetailsViewHolder extends RecyclerView.ViewHolder {
        private TextView matchVenue;
        private TextView matchTitle;
        private TextView matchDate;
        private TextView matchTime;
        private TextView matchOvers;
        private TextView teamAName;
        private TextView teamBName;
        private MaterialCardView matchItem;

        public MatchDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            matchVenue = itemView.findViewById(R.id.match_venue);
            teamAName = itemView.findViewById(R.id.team_a_name);
            teamBName = itemView.findViewById(R.id.team_b_name);
            matchItem = itemView.findViewById(R.id.matchItem);
            matchTitle = itemView.findViewById(R.id.match_title);
//            matchDate = itemView.findViewById(R.id.match_date);
//            matchTime = itemView.findViewById(R.id.match_time);
            matchOvers = itemView.findViewById(R.id.match_overs);
        }
    }

    public interface OnClickListener {
        void onClick(Integer matchId);
    }
}
