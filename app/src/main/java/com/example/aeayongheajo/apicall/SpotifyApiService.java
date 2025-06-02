package com.example.aeayongheajo.apicall;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface SpotifyApiService {
    @GET("v1/playlists/{playlist_id}/tracks")
    Call<PlaylistTracksResponse> getPlaylistTracks(
            @Header("Authorization") String authHeader,
            @Path("playlist_id") String playlistId
    );
}
