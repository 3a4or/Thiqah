package com.example.mohamedashour.weatherapp.ui.activities.base.mvp

interface IBasePresenter<T> {
    fun onViewActive(t: T)
    fun onViewInactive()
}