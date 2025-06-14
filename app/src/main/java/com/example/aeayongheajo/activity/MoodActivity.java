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

public class MoodActivity extends AppCompatActivity {

    String selectedGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_survey);
        selectedGenre = getIntent().getStringExtra("genre");
    }


    //뒤로가기
    public void onBackClicked(View view) {
        finish(); // 공통 동작
    }

    //홈으로 가기
    public void onHomeClicked(View view) {
        Intent intent = new Intent(this,  AppStartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    //다음버튼 클릭
    public void onNextClicked(View view) {
        Toast.makeText(this, "분위기를 선택해주세요", Toast.LENGTH_SHORT).show();
    }

    //플레이리스트 가기
    /*public void onPlaylistClicked(View view) {
        Intent intent = new Intent(this, PlaylistActivity.class); // 이동할 화면 이름
        startActivity(intent);
    }*/

    //분위기 선택
    public void onMoodClick(View view) {
        Button clickedButton = (Button) view;
        String selectedMood = clickedButton.getText().toString();

        Intent intent = new Intent(this, RecommendResultActivity.class);
        intent.putExtra("mood", selectedMood);
        intent.putExtra("genre", selectedGenre);
        startActivity(intent);
    }

}