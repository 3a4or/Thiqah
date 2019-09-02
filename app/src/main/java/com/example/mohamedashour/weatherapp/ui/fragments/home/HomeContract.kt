package com.example.mohamedashour.weatherapp.ui.fragments.home

import com.example.mohamedashour.weatherapp.data.db.entities.Movie
import com.example.mohamedashour.weatherapp.data.models.Result
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBasePresenter
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBaseView

interface HomeContract {
    interface HomeView : IBaseView {
        fun receiveData(list : List<Result>)
        fun receiveFavorites(list: List<Movie>)
        fun movieDeleted()
    }
    interface HomePresenter : IBasePresenter<HomeView> {
        fun getHomeData()
        fun getHighestMovies()
        fun getFavoriteMovies()
        fun deleteMovie(movie: Movie)
    }
}