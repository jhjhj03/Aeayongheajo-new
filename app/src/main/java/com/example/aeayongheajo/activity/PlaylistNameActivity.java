// PlaylistNameActivity.java
package com.example.aeayongheajo.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aeayongheajo.MyDatabaseHelper;
import com.example.aeayongheajo.R;

import java.util.HashMap;
import java.util.Map;

public class PlaylistNameActivity extends AppCompatActivity {

    private VideoView videoView;
    private EditText editTextTitle;
    private String playlistTitle;
    private String playlistId;
    private String videoKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_listname);

        ViewGroup container = findViewById(R.id.container);
        View content = getLayoutInflater().inflate(R.layout.activity_playlist_name, container, false);
        container.addView(content);

        ImageView back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> finish());

        videoView = content.findViewById(R.id.videoView);
        editTextTitle = content.findViewById(R.id.editTextTitle);

        Intent intent = getIntent();
        playlistTitle = intent.getStringExtra("title");
        playlistId = intent.getStringExtra("playlistId");
        videoKey = intent.getStringExtra("videoKey");

        Uri videoUri = getVideoUriByKey(videoKey);
        if (videoUri != null) {
            videoView.setVideoURI(videoUri);
            videoView.setOnPreparedListener(mp -> mp.setLooping(true));
            videoView.start();
        } else {
            Toast.makeText(this, "비디오를 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
        }

        LinearLayout saveButton = content.findViewById(R.id.linearLayout);
        saveButton.setOnClickListener(v -> {
            String titleInput = editTextTitle.getText().toString().trim();
            if (titleInput.isEmpty()) {
                Toast.makeText(this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Log.d("DEBUG", "videoKey for DB: " + videoKey);

            int videoId = getVideoIdFromKey(db, videoKey);
            if (videoId == -1) {
                Toast.makeText(this, "해당 분위기의 영상 정보가 없습니다", Toast.LENGTH_SHORT).show();
                return;
            }

            db.execSQL("INSERT INTO Playlist (playlist_id, playlist_title, video_id) VALUES (?, ?, ?)",
                    new Object[]{playlistId, titleInput, videoId});

            Toast.makeText(this, "플레이리스트가 저장되었습니다", Toast.LENGTH_SHORT).show();

            Intent homeIntent = new Intent(this, AppStartActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
            finish();
        });
    }

    private Uri getVideoUriByKey(String key) {
        Map<String, Integer> videoMap = new HashMap<>();
        videoMap.put("국내 발라드_사랑", R.raw.video_love);
        videoMap.put("국내 발라드_이별", R.raw.video_goodbye);
        videoMap.put("해외 팝_운동", R.raw.video_workout);

        if (videoMap.containsKey(key)) {
            return Uri.parse("android.resource://" + getPackageName() + "/" + videoMap.get(key));
        }
        return null;
    }


    private int getVideoIdFromKey(SQLiteDatabase db, String videoKey) {
        int videoId = -1;
        Cursor cursor = db.rawQuery("SELECT video_id FROM Video WHERE mood = ?", new String[]{videoKey});
        if (cursor.moveToFirst()) {
            videoId = cursor.getInt(0);
        }
        cursor.close();
        return videoId;
    }

}
