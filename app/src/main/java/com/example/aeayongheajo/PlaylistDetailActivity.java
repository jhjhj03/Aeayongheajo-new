package com.example.aeayongheajo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 플레이리스트 상세 화면을 담당하는 액티비티입니다.
 * 사용자는 이 화면에서 플레이리스트를 재생하거나,
 * 하단 네비게이션 바를 통해 다른 화면으로 이동할 수 있습니다.
 */
public class PlaylistDetailActivity extends AppCompatActivity {

    // 상단 뒤로가기 버튼
    private ImageView backButton;

    // 재생 버튼을 감싸는 레이아웃
    private LinearLayout playButtonLayout;

    // 하단 네비게이션 바 아이콘들
    private ImageView navHome, navBack, navNext, navPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_toolbar_play);

        // 1. 화면 요소 초기화
        initViews();

        // 2. 이벤트 리스너 등록
        setListeners();

        // 3. 인텐트로 전달된 데이터 처리 (예: 어떤 플레이리스트인지 정보가 필요할 때)
        handleIntentData();

        // 4. 플레이리스트 항목 로딩 및 UI 표시 (추후 구현 예정)
        // ex: loadPlaylistDetails();
    }

    /**
     * XML 레이아웃에 정의된 View들을 찾아 초기화합니다.
     */
    private void initViews() {
        backButton = findViewById(R.id.backButton);
        playButtonLayout = findViewById(R.id.playButtonLayout);

        navHome = findViewById(R.id.navHome);
        navBack = findViewById(R.id.navBack);
        navNext = findViewById(R.id.navNext);
        navPlaylist = findViewById(R.id.navPlaylist);
    }

    /**
     * 사용자 이벤트에 대한 리스너를 설정합니다.
     */
    private void setListeners() {
        // 상단 뒤로가기 버튼: 현재 액티비티 종료
        backButton.setOnClickListener(v -> {
            finish();
        });

        // 재생 버튼 클릭 시
        playButtonLayout.setOnClickListener(v -> {
            // TODO: MediaPlayer를 사용하여 실제 플레이리스트 음악 재생 구현 예정
            Toast.makeText(this, "플레이리스트 재생 시작!", Toast.LENGTH_SHORT).show();
        });

        // 하단 네비게이션 바
        navHome.setOnClickListener(v -> {
            // 메인 화면으로 이동
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        navBack.setOnClickListener(v -> {
            // 기본 백 버튼 동작
            onBackPressed();
        });

        navNext.setOnClickListener(v -> {
            // 향후 기능 확장 예정
            Toast.makeText(this, "다음 곡 기능은 준비 중입니다", Toast.LENGTH_SHORT).show();
        });

        navPlaylist.setOnClickListener(v -> {
            // 플레이리스트 목록으로 돌아가기
            Intent intent = new Intent(this, PlaylistListActivity.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * 인텐트로 전달된 데이터를 수신하여 처리합니다.
     * 예: 선택된 플레이리스트의 ID나 이름 등
     */
    private void handleIntentData() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("playlistId")) {
            String playlistId = intent.getStringExtra("playlistId");
            // TODO: playlistId를 기반으로 데이터베이스 또는 API에서 해당 정보 불러오기
            // 예: loadPlaylistById(playlistId);
        }
    }

    /**
     * (예정) 플레이리스트 상세 정보를 로딩하고 UI에 표시합니다.
     * - 실제 곡 리스트를 RecyclerView로 표시할 수 있음
     * - 현재는 레이아웃 파일(layout_base_toolbar_play)에 TextView나 ListView가 있는지 확인 필요
     */
    private void loadPlaylistDetails() {
        // TODO: RecyclerView 또는 ScrollView를 이용해 플레이리스트 곡 정보를 동적으로 구성
        // 예: 서버에서 데이터를 받아오거나 로컬에 저장된 곡 리스트 출력
    }
}
