package com.example.aeayongheajo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aeayongheajo.R;
import com.example.aeayongheajo.apicall.AuthClient;
import com.example.aeayongheajo.apicall.PlaylistTracksResponse;
import com.example.aeayongheajo.apicall.RecommendationResponse;
import com.example.aeayongheajo.apicall.SpotifyRepository;
import com.example.aeayongheajo.apicall.TokenResponse;
import com.example.aeayongheajo.apicall.TrackAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendResultActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "e4497218f3a64fecbc29d884e1630412";
    private static final String CLIENT_SECRET = "10f748e16ff04372bc1376dd19879e7c";

    private static final Map<String, String[]> playlistMap = new HashMap<>() {{
        put("국내 발라드_사랑", new String[]{"7vWCeEkJYT1HpD60ywcoPn", "4AGCHO8bpmy74lNwfSJLvy"});
        put("국내 발라드_이별", new String[]{"1ugni6qfr4xFryAApxSDwk", "1rWzWIjvHrk1lsfzc7t8NU"});
        put("해외 팝_운동", new String[]{"0GsodFrsaYD9vnOveYGmT9", "1XePcDiP7MOrrdpogIgvSX"});
        put("default", new String[]{"0BPnOmtmrT2lryqVPCTPrU"});
    }};

    private String accessToken;
    private int currentIndex = 0;
    private RecyclerView recyclerView;
    private TrackAdapter adapter;
    private String[] playlistIds;
    private String videoKey;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_toolbar_rerole);

        ViewGroup container = findViewById(R.id.container);
        View content = LayoutInflater.from(this).inflate(R.layout.activity_recommend_result, container, false);
        container.addView(content);

        Intent intent = getIntent();
        String genre = intent.getStringExtra("genre").trim();
        String mood = intent.getStringExtra("mood").trim();
        videoKey = genre + "_" + mood;

        Log.d("DEBUG", "Genre: " + genre);
        Log.d("DEBUG", "Mood: " + mood);
        Log.d("DEBUG", "videoKey: " + videoKey);

        playlistIds = playlistMap.getOrDefault(videoKey, playlistMap.get("default"));

        // 생성 버튼 클릭 시 이름 설정 화면으로
        LinearLayout generateLayout = content.findViewById(R.id.generateLayout);
        generateLayout.setOnClickListener(v -> {
            Intent nameIntent = new Intent(this, PlaylistNameActivity.class);
            nameIntent.putExtra("playlistId", playlistIds[currentIndex]);
            nameIntent.putExtra("videoKey", videoKey);
            nameIntent.putExtra("title", genre + " " + mood + " 플리");
            startActivity(nameIntent);
        });

        recyclerView = content.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageView btnReroll = findViewById(R.id.btnReroll);
        btnReroll.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % playlistIds.length;
            fetchPlaylistTracks(playlistIds[currentIndex]);
            Log.d("DEBUG", "Re-rolled! Now using: " + Arrays.toString(playlistIds));
        });


        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        //토큰 발급
        AuthClient.fetchAccessToken(CLIENT_ID, CLIENT_SECRET, new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    accessToken = response.body().accessToken;
                    fetchPlaylistTracks(playlistIds[currentIndex]);
                } else {
                    Log.e("TOKEN_FAIL", "Access token error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e("TOKEN_ERROR", t.getMessage());
            }
        });
    }

    private void fetchPlaylistTracks(String playlistId) {
        SpotifyRepository repository = new SpotifyRepository();
        repository.fetchPlaylistTracks(accessToken, playlistId, new Callback<PlaylistTracksResponse>() {
            @Override
            public void onResponse(Call<PlaylistTracksResponse> call, Response<PlaylistTracksResponse> response) {
                if (response.isSuccessful()) {
                    List<PlaylistTracksResponse.Item> items = response.body().items;

                    List<RecommendationResponse.Track> tracks = new ArrayList<>();
                    for (PlaylistTracksResponse.Item item : items) {
                        PlaylistTracksResponse.Track t = item.track;
                        RecommendationResponse.Track track = new RecommendationResponse.Track();
                        track.name = t.name;

                        track.artists = new ArrayList<>();
                        for (PlaylistTracksResponse.Artist a : t.artists) {
                            RecommendationResponse.Artist ra = new RecommendationResponse.Artist();
                            ra.name = a.name;
                            track.artists.add(ra);
                        }

                        track.album = new RecommendationResponse.Album();
                        track.album.images = new ArrayList<>();
                        for (PlaylistTracksResponse.Image img : t.album.images) {
                            RecommendationResponse.Image ri = new RecommendationResponse.Image();
                            ri.url = img.url;
                            track.album.images.add(ri);
                        }

                        tracks.add(track);
                    }

                    adapter = new TrackAdapter(tracks);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("PLAYLIST_FAIL", "Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PlaylistTracksResponse> call, Throwable t) {
                Log.e("PLAYLIST_ERROR", t.getMessage());
            }
        });
    }
    //하단바 네비게이션
    //홈으로 가기
    public void onHomeClicked(View view) {
        Intent intent = new Intent(this,  AppStartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    //뒤로가기
    public void onBackClicked(View view) {
        finish(); // 공통 동작
    }
    //다음버튼클릭
    public void onNextClicked(View view) {
        Toast.makeText(this, "플레이리스트 생성버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
    }
    //플레이리스트 가기
    public void onPlaylistClicked(View view) {
        Intent intent = new Intent(this, PlaylistListActivity.class); // 이동할 화면 이름
        startActivity(intent);
    }


}
