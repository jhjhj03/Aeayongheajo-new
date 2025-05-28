package com.example.aeayongheajo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // base 틀 레이아웃 적용
        setContentView(R.layout.layout_base_toolbar2);

        // 중간 container에 로딩 콘텐츠 삽입
        ViewGroup container = findViewById(R.id.container);
        View content = getLayoutInflater().inflate(R.layout.activity_loading, container, false);
        container.addView(content);

        // 뒤로가기 버튼 이벤트 처리
        ImageView back = findViewById(R.id.btnBack);
        if (back != null) {
            back.setOnClickListener(v -> finish());
        }
    }
}
