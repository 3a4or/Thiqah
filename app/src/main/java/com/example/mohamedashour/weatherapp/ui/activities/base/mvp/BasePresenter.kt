package com.example.mohamedashour.weatherapp.ui.activities.base.mvp

open class BasePresenter<T> : IBasePresenter<T>{

    protected var view: T? = null

    override fun onViewActive(t: T) {
        view = t
    }

    override fun onViewInactive() {
        view = null
    }
}