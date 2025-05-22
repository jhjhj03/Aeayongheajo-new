package com.example.aeayongheajo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
    private List<RecommendationResponse.Track> trackList;

    public TrackAdapter(List<RecommendationResponse.Track> trackList) {
        this.trackList = trackList;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_track, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        RecommendationResponse.Track track = trackList.get(position);
        holder.trackName.setText(track.name);
        holder.artistName.setText(track.artists.get(0).name);
        Glide.with(holder.itemView.getContext())
                .load(track.album.images.get(0).url)
                .into(holder.albumImage);
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    static class TrackViewHolder extends RecyclerView.ViewHolder {
        TextView trackName, artistName;
        ImageView albumImage;

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            trackName = itemView.findViewById(R.id.trackName);
            artistName = itemView.findViewById(R.id.artistName);
            albumImage = itemView.findViewById(R.id.albumImage);
        }
    }
}
