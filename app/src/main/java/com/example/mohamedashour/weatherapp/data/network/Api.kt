package com.example.mohamedashour.weatherapp.data.network

import com.example.mohamedashour.weatherapp.data.models.FakeModel
import com.example.mohamedashour.weatherapp.data.models.MovieDetails
import com.example.mohamedashour.weatherapp.data.models.Posts
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Url
import retrofit2.http.GET

interface Api {

    @GET
    fun getMovieDetails(@Url url: String): Call<MovieDetails>

    @DELETE
    fun deletePost(@Url url: String) : Call<JsonElement>

    @GET
    fun getPosts(@Url url: String): Call<ArrayList<Posts>>
}