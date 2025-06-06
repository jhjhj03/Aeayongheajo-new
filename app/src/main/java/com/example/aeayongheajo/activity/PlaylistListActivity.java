package com.example.aeayongheajo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aeayongheajo.MyDatabaseHelper;
import com.example.aeayongheajo.Playlist;
import com.example.aeayongheajo.R;

import java.util.List;

/**
 * 사용자가 선택할 수 있는 플레이리스트 목록 화면
 * 클릭 시 PlaylistDetailActivity로 이동하며, 제목 데이터를 전달
 */
public class PlaylistListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlaylistAdapter adapter;
    private List<Playlist> playlistList;
    private ImageView btnBack;  // 뒤로가기 버튼 변수 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_toolbar_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        playlistList = loadPlaylistsFromDb(); // SQLite에서 불러오기
        adapter = new PlaylistAdapter(this, playlistList);
        recyclerView.setAdapter(adapter);

        // 1. 뒤로가기 버튼 연결
        btnBack = findViewById(R.id.btnBack);

        // 2. 클릭 리스너 등록
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(PlaylistListActivity.this, AppStartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // 현재 액티비티 종료
        });
    }

    private List<Playlist> loadPlaylistsFromDb() {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        return dbHelper.getAllPlaylists();  // 단, getAllPlaylists()가 정의되어 있어야 함
    }



}
