package com.example.aeayongheajo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

    private final Context context;
    private final List<Playlist> playlistList;

    public PlaylistAdapter(Context context, List<Playlist> playlistList) {
        this.context = context;
        this.playlistList = playlistList;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_base_toolbar_list, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        Playlist playlist = playlistList.get(position);
        holder.tvTitle.setText(playlist.getTitle());

        // 리스트 아이템 클릭 → 상세 화면으로 이동
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlaylistDetailActivity.class);
            intent.putExtra("playlistTitle", playlist.getTitle());
            intent.putExtra("playlistId", playlist.getId());
            context.startActivity(intent);
        });

        // 재생 버튼 클릭
        holder.btnPlay.setOnClickListener(v -> {
            Toast.makeText(context, "재생: " + playlist.getTitle(), Toast.LENGTH_SHORT).show();
            // TODO: MediaPlayer 재생 기능 추가
        });
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    static class PlaylistViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageButton btnPlay;

        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvPlaylistTitle);
            btnPlay = itemView.findViewById(R.id.btn_play);
        }
    }
}
