package com.example.aeayongheajo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "playlist.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Video Table
        db.execSQL("CREATE TABLE Video (" +
                "video_id INTEGER PRIMARY KEY," +
                "video_url TEXT NOT NULL," +
                "mood TEXT NOT NULL" +
                ");");

        // Playlist Table
        db.execSQL("CREATE TABLE Playlist (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "playlist_id TEXT NOT NULL," +
                "playlist_title TEXT NOT NULL," +
                "video_id INTEGER," +
                "FOREIGN KEY (video_id) REFERENCES Video(video_id) ON DELETE SET NULL" +
                ");");

        // Track Table
        db.execSQL("CREATE TABLE Track (" +
                "track_id TEXT PRIMARY KEY," +
                "track_name TEXT NOT NULL," +
                "artist_name TEXT NOT NULL," +
                "album_name TEXT," +
                "album_cover_url TEXT," +
                "playlist_id INTEGER NOT NULL," +
                "FOREIGN KEY (playlist_id) REFERENCES Playlist(id) ON DELETE CASCADE" +
                ");");

       // Video 테이블 초기값 삽입
        db.execSQL("INSERT INTO Video (video_id, video_url, mood) VALUES (1, 'video_love.mp4', '국내 발라드_사랑');");
        db.execSQL("INSERT INTO Video (video_id, video_url, mood) VALUES (2, 'video_goodbye.mp4', '국내 발라드_이별');");
//        db.execSQL("INSERT INTO Video (video_id, video_url, mood) VALUES (3, 'video_workout.mp4', '해외 팝_운동');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Track");
        db.execSQL("DROP TABLE IF EXISTS Playlist");
        db.execSQL("DROP TABLE IF EXISTS Video");
        onCreate(db);
    }

    public List<Playlist> getAllPlaylists() {
        List<Playlist> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, playlist_title, mood FROM Playlist " +
                "JOIN Video ON Playlist.video_id = Video.video_id", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String mood = cursor.getString(2);
            list.add(new Playlist(id, title, mood));
        }
        cursor.close();
        db.close();
        return list;
    }

}
