package comexample.matches_simulator_digitalinnovationone.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import comexample.matches_simulator_digitalinnovationone.databinding.MatchItemBinding;
import comexample.matches_simulator_digitalinnovationone.domain.Match;
import comexample.matches_simulator_digitalinnovationone.ui.DetailActivity;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    private List<Match> matches;

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    public MatchesAdapter() {

    }

    public List<Match> getMatches() {
        return matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MatchItemBinding binding = MatchItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Match match = matches.get(position);

        Glide.with(context).load(match.getHomeTeam().getImage()).into(holder.binding.ivHomeTeamOne);
        holder.binding.tvHomeTeamOne.setText(match.getHomeTeam().getName());
        if(match.getHomeTeam().getScore() != null){
            holder.binding.tvHomeScoreBoardOne.setText(String.valueOf(match.getHomeTeam().getScore()));
        }


        Glide.with(context).load(match.getAwayTeam().getImage()).into(holder.binding.ivAwayTeamTwo);
        holder.binding.tvAwayTeamTwo.setText(match.getAwayTeam().getName());
        if(match.getAwayTeam().getScore() != null){
            holder.binding.tvHomeScoreBoardTwo.setText(String.valueOf(match.getAwayTeam().getScore()));
        }

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.Extras.MATCH, match);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final MatchItemBinding binding;

        public ViewHolder(MatchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
