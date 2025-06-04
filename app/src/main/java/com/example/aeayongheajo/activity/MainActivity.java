
package com.example.aeayongheajo.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aeayongheajo.MyDatabaseHelper;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // MyDatabaseHelper를 통해 데이터베이스를 생성
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);

        // getWritableDatabase() 호출로 데이터베이스 생성 및 onCreate() 메서드 실행
        SQLiteDatabase db = dbHelper.getWritableDatabase();  // 데이터베이스가 생성됨



    }
}