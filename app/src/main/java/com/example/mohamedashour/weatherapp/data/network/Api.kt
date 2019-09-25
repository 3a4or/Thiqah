package com.example.mohamedashour.weatherapp.data.network

import com.example.mohamedashour.weatherapp.data.models.PostRequest
import com.example.mohamedashour.weatherapp.data.models.Posts
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @POST("posts")
    fun addPost(@Body request: PostRequest): Call<Posts>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") id: String, @Body request: PostRequest): Call<Posts>

    @DELETE
    fun deletePost(@Url url: String) : Call<JsonElement>

    @GET
    fun getPosts(@Url url: String): Call<ArrayList<Posts>>
}