package com.example.mohamedashour.weatherapp.ui.activities.base

import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.BasePresenter
import com.example.mohamedashour.weatherapp.utils.AppTools

class BaseActivityPresenter(view: BaseContract.BaseView) : BasePresenter<BaseContract.BaseView>(), BaseContract.BasePresenter {

    init {
        this.view = view
    }

    override fun openFragmentPage(type: String) {
        when (type) {
            AppTools.POST_DETAILS -> {
                view!!.openPostDetailsPage()
            }
        }
    }
}