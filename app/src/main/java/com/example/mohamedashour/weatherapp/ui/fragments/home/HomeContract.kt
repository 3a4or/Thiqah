package com.example.mohamedashour.weatherapp.ui.fragments.home

import com.example.mohamedashour.weatherapp.data.models.Posts
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBasePresenter
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.IBaseView

interface HomeContract {
    interface HomeView : IBaseView {
        fun receiveData(list : ArrayList<Posts>, type: String)
        fun postDeleted(tem: Posts)
        fun errorTitle()
        fun errorBody()
        fun validationDone(id: Int, userId: Int, type: String, index: Int)
        fun requestFinished(type: String, newModel: Posts, index: Int)
    }
    interface HomePresenter : IBasePresenter<HomeView> {
        fun getHomeData(start: Int, type: String)
        fun deletePost(id: Int, item: Posts)
        fun validation(title: String, body: String, id: Int, userId: Int, type: String, index: Int)
        fun updatePost(title: String, body: String, id: Int, userId: Int, type: String, index: Int)
        fun addPost(title: String, body: String, userId: Int, type: String)
    }
}