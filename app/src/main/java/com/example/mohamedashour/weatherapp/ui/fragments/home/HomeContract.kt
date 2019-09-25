package com.example.mohamedashour.weatherapp.ui.fragments.home

import com.example.mohamedashour.weatherapp.data.models.Posts
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBasePresenter
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBaseView

interface HomeContract {
    interface HomeView : IBaseView {
        fun receiveData(list : ArrayList<Posts>)
        fun postDeleted(tem: Posts)
    }
    interface HomePresenter : IBasePresenter<HomeView> {
        fun getHomeData(start: Int)
        fun deletePost(id: Int, item: Posts)
    }
}