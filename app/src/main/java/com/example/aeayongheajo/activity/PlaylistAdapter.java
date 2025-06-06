package com.example.aeayongheajo.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aeayongheajo.Playlist;
import com.example.aeayongheajo.R;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private List<Playlist> playlists;
    private Context context;

    public PlaylistAdapter(Context context, List<Playlist> playlists) {
        this.context = context;
        this.playlists = playlists;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumbnail;
        TextView textTitle;
        ImageButton btnPlay;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            textTitle = itemView.findViewById(R.id.textTitle);
            btnPlay = itemView.findViewById(R.id.btnPlay);
        }
    }

    @Override
    public PlaylistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaylistAdapter.ViewHolder holder, int position) {
        Playlist playlist = playlists.get(position);

        holder.textTitle.setText(playlist.getTitle());
        Log.d("PlaylistAdapter", "Mood: [" + playlist.getMood() + "]");

        switch (playlist.getMood().trim()) {
            case "국내 발라드_사랑":
                holder.imgThumbnail.setImageResource(R.drawable.picture_love);
                break;
            case "국내 발라드_이별":
                holder.imgThumbnail.setImageResource(R.drawable.picture_goodbye);
                break;
            case "해외 팝_운동":
                holder.imgThumbnail.setImageResource(R.drawable.picture_workout);
                break;
            default:
                holder.imgThumbnail.setImageResource(R.drawable.picture_bird);
                break;
        }


        holder.btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlaylistDetailActivity.class);
            intent.putExtra("playlistId", playlist.getId());    // ID 전달
            intent.putExtra("playlistTitle", playlist.getTitle());  // 제목도 필요 시 전달
            intent.putExtra("mood", playlist.getMood());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

}
