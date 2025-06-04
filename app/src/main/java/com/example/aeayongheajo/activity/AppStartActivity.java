package com.example.aeayongheajo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aeayongheajo.R;

public class AppStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_start);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.appStart), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnCreatePlaylist = findViewById(R.id.btn_create_playlist);
        Button btnPlaylist = findViewById(R.id.btn_playlist);

        btnCreatePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppStartActivity.this, GenreActivity.class);
                startActivity(intent);
            }
        });

        btnPlaylist.setOnClickListener(v -> {
            Intent intent = new Intent(AppStartActivity.this, PlaylistListActivity.class);
            startActivity(intent);
        });

    }

    //플레이리스트 가기
    /*public void onPlaylistClicked(View view) {
        Intent intent = new Intent(this, PlaylistActivity.class); // 이동할 화면 이름
        startActivity(intent);
    }*/
}