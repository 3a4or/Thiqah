package com.example.mohamedashour.weatherapp.ui.fragments.moviereviews

import com.example.mohamedashour.weatherapp.data.models.MovieReviews
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBasePresenter
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBaseView

interface MovieReviewsContract {

    interface MovieReviewsView : IBaseView {
        fun receiveData(list: List<MovieReviews.Result>)
    }

    interface MovieReviewsPresenter : IBasePresenter<MovieReviewsView> {
        fun getData(id: String)
    }
}