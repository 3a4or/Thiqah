package com.example.mohamedashour.weatherapp.data.network

import com.example.mohamedashour.weatherapp.data.models.MovieDetails
import com.example.mohamedashour.weatherapp.data.models.MovieReviews
import com.example.mohamedashour.weatherapp.data.models.PopularMovies
import retrofit2.Call
import retrofit2.http.Url
import retrofit2.http.GET

interface Api {
    @GET
    fun getMovies(@Url url: String): Call<PopularMovies>

    @GET
    fun getMovieDetails(@Url url: String): Call<MovieDetails>

    @GET
    fun getMovieReviews(@Url url: String): Call<MovieReviews>

    @GET
    fun getHighestMovies(@Url url: String): Call<PopularMovies>
}