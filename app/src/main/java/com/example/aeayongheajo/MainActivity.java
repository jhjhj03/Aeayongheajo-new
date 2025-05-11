package com.example.aeayongheajo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // MyDatabaseHelper를 통해 데이터베이스를 생성
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);

        // getWritableDatabase() 호출로 데이터베이스 생성 및 onCreate() 메서드 실행
        SQLiteDatabase db = dbHelper.getWritableDatabase();  // 데이터베이스가 생성됨

    }
}