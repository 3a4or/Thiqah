package com.example.mohamedashour.weatherapp.ui.fragments.home

import android.util.Log
import com.example.mohamedashour.weatherapp.data.models.PostRequest
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

    override fun validation(title: String, body: String, id: Int, userId: Int, type: String, index: Int) {
        if (title == "") {
            view!!.errorTitle()
        }
        if (body == "") {
            view!!.errorBody()
        }
        if (title != "" && body != "") {
            view!!.validationDone(id, userId, type, index)
        }
    }

    override fun addPost(title: String, body: String, userId: Int, type: String) {
        view!!.setProgressBar(true)
        var request = PostRequest(body, title, userId)
        val call: Call<Posts> = RetrofitClient.webService().addPost(request)
        call.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                view!!.setProgressBar(false)
                view!!.requestFinished(type, response.body()!!, 0)
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                view!!.setProgressBar(false)
            }
        })
    }

    override fun updatePost(title: String, body: String, id: Int, userId: Int, type: String, index: Int) {
        view!!.setProgressBar(true)
        var request = PostRequest(body, title, userId)
        val call: Call<Posts> = RetrofitClient.webService().updatePost(id.toString(), request)
        call.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                view!!.setProgressBar(false)
                view!!.requestFinished(type, response.body()!!, index)
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                view!!.setProgressBar(false)
            }
        })
    }
}