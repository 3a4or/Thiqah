package com.example.mohamedashour.weatherapp.ui.fragments.home

import android.util.Log
import com.example.mohamedashour.weatherapp.data.models.Posts
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.BasePresenter
import com.example.mohamedashour.weatherapp.data.network.RetrofitClient
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(view: HomeContract.HomeView) : BasePresenter<HomeContract.HomeView>(), HomeContract.HomePresenter{

    init {
        this.view = view
    }

    override fun getHomeData(start: Int, type: String) {
        view!!.setProgressBar(true)
        val call: Call<ArrayList<Posts>> = RetrofitClient.webService().getPosts("posts?_start=$start&_limit=15")
        call.enqueue(object : Callback<ArrayList<Posts>> {

            override fun onResponse(call: Call<ArrayList<Posts>>, response: Response<ArrayList<Posts>>) {
                view!!.setProgressBar(false)
                if (response.body() != null) {
                    view!!.receiveData(response.body()!!, type)
                }
                if (response.raw().cacheResponse != null) {
                    Log.e("SSS", "response came from cache")
                }
                if (response.raw().networkResponse != null) {
                    Log.e("SSS", "response came from server")
                }
            }

            override fun onFailure(call: Call<ArrayList<Posts>>, t: Throwable) {
                view!!.setProgressBar(false)
            }

        })
    }

    override fun deletePost(id: Int, item: Posts) {
        view!!.setProgressBar(true)
        val call: Call<JsonElement> = RetrofitClient.webService().deletePost("posts/$id")
        call.enqueue(object : Callback<JsonElement> {

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                view!!.setProgressBar(false)
                view!!.postDeleted(item)
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                view!!.setProgressBar(false)
            }

        })
    }

    override fun validation(title: String, body: String, id: Int, userId: Int, type: String) {
        if (title == "") {
            view!!.errorTitle()
        }
        if (body == "") {
            view!!.errorBody()
        }
        if (title != "" && body != "") {
            view!!.validationDone(id, userId, type)
        }
    }

    override fun addPost(title: String, body: String, id: Int, userId: Int, type: String) {

    }

    override fun updatePost(title: String, body: String, id: Int, userId: Int, type: String) {

    }
}