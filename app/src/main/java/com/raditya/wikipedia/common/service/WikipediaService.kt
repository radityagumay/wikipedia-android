package com.raditya.wikipedia.common.service

import com.raditya.wikipedia.data.login.request.LoginRequest
import com.raditya.wikipedia.data.login.response.LoginResponse

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface WikipediaService {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("info/{email}")
    suspend fun info(
        @Path("email") email: String
    ): LoginResponse

    @Multipart
    @POST("upload")
    fun upload(
        @Part("description") description: RequestBody?,
        @Part file: MultipartBody.Part?
    ): Call<ResponseBody>
}