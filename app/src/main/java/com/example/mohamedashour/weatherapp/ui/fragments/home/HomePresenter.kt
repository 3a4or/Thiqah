package com.example.mohamedashour.weatherapp.ui.fragments.home

import com.example.mohamedashour.weatherapp.data.db.AppDatabase
import com.example.mohamedashour.weatherapp.data.db.entities.Movie
import com.example.mohamedashour.weatherapp.data.models.PopularMovies
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.BasePresenter
import com.example.mohamedashour.weatherapp.data.network.RetrofitClient
import com.example.mohamedashour.weatherapp.ui.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(view: HomeContract.HomeView) : BasePresenter<HomeContract.HomeView>(), HomeContract.HomePresenter{

    init {
        this.view = view
    }

    override fun getHomeData() {
        view!!.setProgressBar(true)
        val call: Call<PopularMovies> = RetrofitClient.webService().getMovies("discover/movie?sort_by=popularity.desc&api_key=086b41ec08d37d153d8b3ca86ea0f510")
        call.enqueue(object : Callback<PopularMovies> {

            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                view!!.setProgressBar(false)
                view!!.receiveData(response.body()!!.results!!)
            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                view!!.setProgressBar(false)
            }

        })
    }

    override fun getHighestMovies() {
        view!!.setProgressBar(true)
        val call: Call<PopularMovies> = RetrofitClient.webService().getMovies("discover/movie?sort_by=vote_average.desc&api_key=086b41ec08d37d153d8b3ca86ea0f510")
        call.enqueue(object : Callback<PopularMovies> {

            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                view!!.setProgressBar(false)
                view!!.receiveData(response.body()!!.results!!)
            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                view!!.setProgressBar(false)
            }

        })
    }

    override fun getFavoriteMovies() {
        val movies = AppDatabase.getInstance(App.instance()).movieDao().getAll()
        view!!.receiveFavorites(movies)
    }

    override fun deleteMovie(movie: Movie) {
        AppDatabase.getInstance(App.instance()).movieDao().delete(movie)
        view!!.movieDeleted()
    }
}