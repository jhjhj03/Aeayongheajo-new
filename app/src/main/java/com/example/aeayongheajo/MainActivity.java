package com.example.aeayongheajo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "e4497218f3a64fecbc29d884e1630412";
    private static final String CLIENT_SECRET = "10f748e16ff04372bc1376dd19879e7c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //시스템 UI 패딩 처리
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // MyDatabaseHelper를 통해 데이터베이스를 생성
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);

        // getWritableDatabase() 호출로 데이터베이스 생성 및 onCreate() 메서드 실행
        SQLiteDatabase db = dbHelper.getWritableDatabase();  // 데이터베이스가 생성됨


        // Spotify API 토큰 발급 및 콜백 처리
        AuthClient.fetchAccessToken(CLIENT_ID, CLIENT_SECRET, new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    String accessToken = response.body().accessToken;
                    Log.d("ACCESS_TOKEN", accessToken);

                    // 여기서 추천 API 호출 가능 (예: fetchRecommendations(accessToken))
                } else {
                    Log.e("API 실패", "Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e("API 오류", t.getMessage(), t);
            }
        });
    }
}