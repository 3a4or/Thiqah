package com.example.mohamedashour.weatherapp.ui.fragments.moviedetails

import com.example.mohamedashour.weatherapp.data.db.AppDatabase
import com.example.mohamedashour.weatherapp.data.db.entities.Movie
import com.example.mohamedashour.weatherapp.data.models.MovieDetails
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.BasePresenter
import com.example.mohamedashour.weatherapp.data.network.RetrofitClient
import com.example.mohamedashour.weatherapp.ui.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsPresenter(view: MovieDetailsContract.MovieDetailsView) : BasePresenter<MovieDetailsContract.MovieDetailsView>(), MovieDetailsContract.MovieDetailsPresenter{

    init {
        this.view = view
    }

    override fun getData(id: String) {
        view!!.setProgressBar(true)
        val call: Call<MovieDetails> = RetrofitClient.webService().getMovieDetails("movie/$id/videos?api_key=086b41ec08d37d153d8b3ca86ea0f510")
        call.enqueue(object : Callback<MovieDetails> {

            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                view!!.setProgressBar(false)
                view!!.receiveData(response.body()!!.results)
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                view!!.setProgressBar(false)
            }

        })
    }

    override fun addFavourite(id: String, title: String, date: String, rate: String, desc: String, image: String) {
        val movie = Movie(title = title, date = date, rate = rate, desc = desc, image = image, id = id)
        AppDatabase.getInstance(App.instance()).movieDao().insertAll(movie)
        view!!.addedSuccessfully()
    }
}