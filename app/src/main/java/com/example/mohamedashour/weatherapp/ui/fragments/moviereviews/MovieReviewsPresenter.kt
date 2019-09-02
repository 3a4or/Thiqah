package com.example.mohamedashour.weatherapp.ui.fragments.moviereviews

import com.example.mohamedashour.weatherapp.data.models.MovieReviews
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.BasePresenter
import com.example.mohamedashour.weatherapp.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieReviewsPresenter(view: MovieReviewsContract.MovieReviewsView) : BasePresenter<MovieReviewsContract.MovieReviewsView>(), MovieReviewsContract.MovieReviewsPresenter {

    init {
        this.view = view
    }

    override fun getData(id: String) {
        view!!.setProgressBar(true)
        val call: Call<MovieReviews> = RetrofitClient.webService().getMovieReviews("movie/$id/reviews?api_key=086b41ec08d37d153d8b3ca86ea0f510")
        call.enqueue(object : Callback<MovieReviews> {

            override fun onResponse(call: Call<MovieReviews>, response: Response<MovieReviews>) {
                view!!.setProgressBar(false)
                view!!.receiveData(response.body()!!.results)
            }

            override fun onFailure(call: Call<MovieReviews>, t: Throwable) {
                view!!.setProgressBar(false)
            }

        })
    }
}