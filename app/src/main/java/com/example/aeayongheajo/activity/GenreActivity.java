package com.example.aeayongheajo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aeayongheajo.R;

public class GenreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_genre_survey);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.genre), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //뒤로 가기 버튼
    public void onBackClicked(View view) {
        finish(); // 공통 동작
    }
    //다음 버튼 클릭
    public void onNextClicked(View view) {
        Toast.makeText(this, "장르를 선택해주세요", Toast.LENGTH_SHORT).show();
    }

    //플레이리스트 가기
    /*public void onPlaylistClicked(View view) {
        Intent intent = new Intent(this, PlaylistActivity.class); // 이동할 화면 이름
        startActivity(intent);
    }*/

    //장르 선택시
    public void onGenreClick(View view) {
        Button clickedButton = (Button) view;
        String selectedGenre = clickedButton.getText().toString();

        Intent intent = new Intent(this, MoodActivity.class);
        intent.putExtra("genre", selectedGenre);
        startActivity(intent);
    }



}