package com.raditya.wikipedia.data.login.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message") val message: String,
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("user") val user: User,
        @SerializedName("authorization") val authorization: Auth
    ) {

        data class User(
            @SerializedName("email") val email: String
        )

        data class Auth(
            @SerializedName("access_token") val accessToken: String,
            @SerializedName("token_type") val tokenType: String,
            @SerializedName("expires_in") val expiresIn: Int,
            @SerializedName("scope") val scope: String,
            @SerializedName("refresh_token") val refreshToken: String
        )
    }
}

