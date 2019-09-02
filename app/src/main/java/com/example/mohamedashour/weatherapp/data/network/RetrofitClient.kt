package com.example.mohamedashour.weatherapp.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://api.themoviedb.org/3/"
    var retrofit: Retrofit? = null
    fun webService() : Api {
        if (retrofit == null) {
            val  okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(3600, TimeUnit.SECONDS).readTimeout(3600, TimeUnit.SECONDS)
                .writeTimeout(3600, TimeUnit.SECONDS)
                .build()
            retrofit = Retrofit.Builder().baseUrl(
                BASE_URL
            ).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .build()
        }
        return retrofit!!.create(
            Api::class.java)
    }
}