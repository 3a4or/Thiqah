package com.example.mohamedashour.weatherapp.data.models

data class PopularMovies(val page: Number?, val total_results: Number?, val total_pages: Number?, val results: List<Result>?)

data class Result(val vote_count: Number?, val id: Number?, val video: Boolean?, val vote_average: Number?,
                  val title: String?, val popularity: Number?, val poster_path: String?, val original_language: String?,
                  val original_title: String?, val genre_ids: List<Number>?, val backdrop_path: String?, val adult: Boolean?,
                  val overview: String?, val release_date: String?)