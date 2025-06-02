package com.example.aeayongheajo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpotifyApiService {
    @GET("v1/playlists/{playlist_id}/tracks")
    Call<PlaylistTracksResponse> getPlaylistTracks(
            @Header("Authorization") String authHeader,
            @Path("playlist_id") String playlistId
    );
}
