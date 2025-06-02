package com.example.aeayongheajo.apicall;

import com.google.gson.annotations.SerializedName;


// 토큰 응답 JSON 매핑
public class TokenResponse {

    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("expires_in")
    public int expiresIn;
}
