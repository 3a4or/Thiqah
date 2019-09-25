package com.example.mohamedashour.weatherapp.ui.activities.base


import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBasePresenter
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBaseView

interface BaseContract {

    interface BaseView : IBaseView {
        fun setActionBarTitle(title: String)
        fun showHideActionBar(visibility: Int)
        fun changeActionBarColor()
        fun openPostDetailsPage()
    }

    interface BasePresenter : IBasePresenter<BaseView> {
        fun openFragmentPage(type: String)
    }
}