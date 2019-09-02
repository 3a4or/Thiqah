package com.example.mohamedashour.weatherapp.ui.activities.base.mvp


interface IBaseView {
    fun showToastMessage(message: String)
    fun setProgressBar(show: Boolean)
}