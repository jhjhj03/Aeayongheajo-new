
package com.example.aeayongheajo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aeayongheajo.apicall.AuthClient;
import com.example.aeayongheajo.apicall.PlaylistTracksResponse;
import com.example.aeayongheajo.R;
import com.example.aeayongheajo.apicall.RecommendationResponse;
import com.example.aeayongheajo.apicall.SpotifyRepository;
import com.example.aeayongheajo.apicall.TokenResponse;
import com.example.aeayongheajo.apicall.TrackAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "e4497218f3a64fecbc29d884e1630412";
    private static final String CLIENT_SECRET = "10f748e16ff04372bc1376dd19879e7c";
    private final String[] playlistIds = {
            "7vWCeEkJYT1HpD60ywcoPn",  // 1st playlist
            "4AGCHO8bpmy74lNwfSJLvy"   // 2nd playlist (reroll)
    };

    private String accessToken;
    private int currentIndex = 0;
    private RecyclerView recyclerView;
    private TrackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_toolbar_rerole);

        ViewGroup container = findViewById(R.id.container);
        View content = LayoutInflater.from(this).inflate(R.layout.activity_recommend_result, container, false);
        container.addView(content);

        recyclerView = content.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageView btnReroll = findViewById(R.id.btnReroll);
        btnReroll.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % playlistIds.length;
            fetchPlaylistTracks(playlistIds[currentIndex]);
        });

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

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
}
