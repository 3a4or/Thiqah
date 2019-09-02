package com.example.mohamedashour.weatherapp.ui.fragments.moviedetails

import com.example.mohamedashour.weatherapp.data.models.MovieDetails
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBasePresenter
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBaseView

interface MovieDetailsContract {

    interface MovieDetailsView : IBaseView {
        fun receiveData(list: List<MovieDetails.Result>)
        fun addedSuccessfully()
    }

    interface MovieDetailsPresenter : IBasePresenter<MovieDetailsView> {
        fun getData(id: String)
        fun addFavourite(id: String, title: String, date: String, rate: String, desc: String, image: String)
    }
}