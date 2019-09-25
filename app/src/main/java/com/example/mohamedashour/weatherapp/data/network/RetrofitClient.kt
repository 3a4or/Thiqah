package com.example.mohamedashour.weatherapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mohamedashour.weatherapp.ui.App
import com.example.mohamedashour.weatherapp.ui.activities.main.MainActivity
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit



object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    var retrofit: Retrofit? = null

    val cacheSize = 10 * 1024 * 1024 // 10 MB

    val httpCacheDirectory = File(App.instance().cacheDir, "http-cache")

    val cache = Cache(httpCacheDirectory, cacheSize.toLong())

    val onlineInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        var cacheControl = CacheControl.Builder()
            .maxAge(1, TimeUnit.MINUTES)
            .build()
        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .removeHeader("Pragma")
            .build()
    }

    val offlineInterceptor = Interceptor { chain ->
        var request = chain.request()
        if (!verifyAvailableNetwork(App.instance())) {
            val maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                .removeHeader("Pragma")
                .build()
        }
        chain.proceed(request)
    }

    fun webService() : Api {

        if (retrofit == null) {
            val  okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .cache(Cache(App.instance().cacheDir, 10 * 1024 * 1024))
                .addNetworkInterceptor(onlineInterceptor)
                .addInterceptor(offlineInterceptor)
                /*.connectTimeout(3600, TimeUnit.SECONDS).readTimeout(3600, TimeUnit.SECONDS)
                .writeTimeout(3600, TimeUnit.SECONDS)*/
                .build()
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit!!.create(
            Api::class.java)
    }

    fun verifyAvailableNetwork(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}