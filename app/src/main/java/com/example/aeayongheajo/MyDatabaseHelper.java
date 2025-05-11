package com.example.aeayongheajo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

       /* 추후에 mv url 삽입
        db.execSQL("INSERT INTO Video VALUES (1, 'http://example.com/video1.mp4', 'SPRING');");
        db.execSQL("INSERT INTO Video VALUES (2, 'http://example.com/video2.mp4', 'DRIVE');");
        db.execSQL("INSERT INTO Video VALUES (3, 'http://example.com/video3.mp4', 'MEMORY');");
        db.execSQL("INSERT INTO Video VALUES (4, 'http://example.com/video3.mp4', 'LOVE');");
        db.execSQL("INSERT INTO Video VALUES (5, 'http://example.com/video3.mp4', 'GOODBYE');");
        db.execSQL("INSERT INTO Video VALUES (6, 'http://example.com/video3.mp4', 'WEATHER');"); */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Track");
        db.execSQL("DROP TABLE IF EXISTS Playlist");
        db.execSQL("DROP TABLE IF EXISTS Video");
        onCreate(db);
    }
}
