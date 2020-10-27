package com.example.cricketapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MatchDetailsListAdapter extends RecyclerView.Adapter<MatchDetailsListAdapter.MatchDetailsViewHolder> {

    private List<Match> matchList;
    private OnClickListener onClickListener;

    public MatchDetailsListAdapter(List<Match> matchList) {
        this.matchList = matchList;
    }

    @NonNull
    @Override
    public MatchDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_details_recyclerview_item, parent, false);
        return new MatchDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchDetailsViewHolder holder, int position) {
        if (matchList != null) {
            Match match = matchList.get(position);
            String venue = match.getVenue().trim() + " on " + match.getStartDate() + " • " + match.getStartTime();
            holder.matchVenue.setText(venue);
            holder.teamAName.setText(match.getTeamAname().trim());
            holder.teamBName.setText(match.getTeamBname().trim());
            String matchTitle = match.getTeamAname() + " vs " + match.getTeamBname();
            holder.matchTitle.setText(matchTitle);
//            holder.matchDate.setText(matchDetails.getStartDate());
//            holder.matchTime.setText(matchDetails.getStartTime());
            holder.matchOvers.setText(match.getTotalover().split("\\.")[0]);
            holder.matchMessage.setText(match.getMessage());

            holder.matchItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(match.getMainScreenMatchId(),match.getTeamAname(),match.getTeamBname());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (matchList != null) {
            return matchList.size();
        }
        return 0;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
        notifyDataSetChanged();
    }


    public void setMatchItem(Match matchItem) {
        matchList.add(matchItem);
        notifyItemInserted(matchList.size() - 1);
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
        private TextView matchMessage;
        private MaterialCardView matchItem;

        public MatchDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            matchVenue = itemView.findViewById(R.id.match_venue);
            teamAName = itemView.findViewById(R.id.team_a_name);
            teamBName = itemView.findViewById(R.id.team_b_name);
            matchItem = itemView.findViewById(R.id.matchItem);
            matchTitle = itemView.findViewById(R.id.match_title);
            matchMessage = itemView.findViewById(R.id.match_message);
//            matchDate = itemView.findViewById(R.id.match_date);
//            matchTime = itemView.findViewById(R.id.match_time);
            matchOvers = itemView.findViewById(R.id.match_overs);
        }
    }

    public interface OnClickListener {
        void onClick(Integer matchId,String teamAName, String teamBName);
    }
}
