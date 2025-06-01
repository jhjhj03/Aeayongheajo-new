package com.example.aeayongheajo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 사용자가 선택할 수 있는 플레이리스트 목록 화면
 * 클릭 시 PlaylistDetailActivity로 이동하며, 제목 데이터를 전달
 */
public class PlaylistListActivity extends AppCompatActivity {

    // 상단 뒤로 가기 버튼
    private ImageView backButton;

    // 플레이리스트 항목 레이아웃 (지금은 2개만 존재)
    private LinearLayout item1Layout, item2Layout;

    // 재생 버튼 (item1에만 존재)
    private ImageButton btnPlay1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_toolbar_list);

        // 1. 뒤로 가기 버튼
        backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // 2. 첫 번째 플레이리스트 항목
        item1Layout = findViewById(R.id.playlist_item_1);
        btnPlay1 = findViewById(R.id.btn_play); // 재생 버튼은 item1에만 있음

        item1Layout.setOnClickListener(v -> {
            openPlaylistDetail("지독한 짝사랑 플리");
        });

        btnPlay1.setOnClickListener(v -> {
            Toast.makeText(this, "재생: 지독한 짝사랑 플리", Toast.LENGTH_SHORT).show();
            // TODO: 실제 MediaPlayer를 통한 재생 기능으로 교체
        });

        // 3. 두 번째 플레이리스트 항목
        item2Layout = findViewById(R.id.playlist_item_2);
        item2Layout.setOnClickListener(v -> {
            openPlaylistDetail("카페에서 듣기 좋은 플리");
        });

        // TODO: 이후 플레이리스트가 늘어나면 RecyclerView로 전환 고려
    }

    /**
     * 플레이리스트 상세 화면으로 이동하며 제목을 전달합니다.
     * 추후엔 playlistId도 같이 넘기는 방식이 더 바람직합니다.
     */
    private void openPlaylistDetail(String title) {
        Intent intent = new Intent(this, PlaylistDetailActivity.class);
        intent.putExtra("playlistTitle", title);  // PlaylistDetailActivity에서 getStringExtra로 받음
        startActivity(intent);
    }
}
