package com.example.aeayongheajo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aeayongheajo.R;

/**
 * 플레이리스트 상세 화면을 담당하는 액티비티입니다.
 * 사용자는 이 화면에서 플레이리스트를 재생하거나,
 * 하단 네비게이션 바를 통해 다른 화면으로 이동할 수 있습니다.
 */
public class PlaylistDetailActivity extends AppCompatActivity {

    private ImageView backButton;
    private LinearLayout playButtonLayout;
    private ImageView navHome, navBack, navNext, navPlaylist;

    private ImageView thumbnailImage;
    private VideoView videoView;
    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_toolbar_play);

        // findViewById: 전역 필드와 연결
        backButton = findViewById(R.id.backButton);
        playButtonLayout = findViewById(R.id.playButtonLayout);
        navHome = findViewById(R.id.navHome);
        navBack = findViewById(R.id.navBack);
        navNext = findViewById(R.id.navNext);
        navPlaylist = findViewById(R.id.navPlaylist);

        setListeners(); // 이렇게 마지막에 호출해야 함!

        thumbnailImage = findViewById(R.id.thumbnailImage);
        videoView = findViewById(R.id.videoThumbnail);
        titleView = findViewById(R.id.playlistTitle);

        // 인텐트로부터 전달된 데이터 처리
        Intent intent = getIntent();
        String mood = intent.getStringExtra("mood");
        String playlistTitle = intent.getStringExtra("playlistTitle");

        int videoResId = R.raw.video_goodbye;  // 기본값
        int imageResId = R.drawable.picture_goodbye;

        if (mood != null) {
            switch (mood.trim()) {
                case "국내 발라드_사랑":
                    videoResId = R.raw.video_love;
                    imageResId = R.drawable.picture_love;
                    break;
                case "국내 발라드_이별":
                    videoResId = R.raw.video_goodbye;
                    imageResId = R.drawable.picture_goodbye;
                    break;
                case "해외 팝_운동":
                    videoResId = R.raw.video_workout;
                    imageResId = R.drawable.picture_workout;
                    break;
            }
        }

        // 썸네일과 제목 표시
        thumbnailImage.setImageResource(imageResId);
        titleView.setText(playlistTitle);

        // MediaController 연결
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // 재생 버튼 클릭 시 동작
        int finalVideoResId = videoResId;
        playButtonLayout.setOnClickListener(v -> {
            thumbnailImage.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            String videoPath = "android.resource://" + getPackageName() + "/" + finalVideoResId;
            videoView.setVideoPath(videoPath);
            videoView.start();
        });

        setListeners();
    }

    private void setListeners() {
        backButton.setOnClickListener(v -> finish());

        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        navBack.setOnClickListener(v -> onBackPressed());

        navNext.setOnClickListener(v -> {
            Toast.makeText(this, "다음 곡 기능은 준비 중입니다", Toast.LENGTH_SHORT).show();
        });

        navPlaylist.setOnClickListener(v -> {
            Intent intent = new Intent(this, PlaylistListActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void handleIntentData() {
        // 향후 확장용
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("playlistId")) {
            String playlistId = intent.getStringExtra("playlistId");
            // 추후 playlistId 기반 DB 조회 가능
        }
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onHomeClicked(View view) {
        Intent intent = new Intent(this, AppStartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
