package com.example.aeayongheajo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aeayongheajo.R;

public class PlaylistNameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_listname);

        // 중간 영역에 추천 결과 레이아웃 붙이기
        ViewGroup container = findViewById(R.id.container);
        View content = getLayoutInflater().inflate(R.layout.activity_playlist_name, container, false);
        container.addView(content);


        // 상단/하단 이벤트 처리 예시
        ImageView back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> finish());
    }
}