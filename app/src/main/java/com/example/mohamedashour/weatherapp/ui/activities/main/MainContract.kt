package com.example.mohamedashour.weatherapp.ui.activities.main


import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBasePresenter
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBaseView

interface MainContract {

    interface MainView : IBaseView {

    }

    interface MainPresenter : IBasePresenter<MainView> {

    }
}