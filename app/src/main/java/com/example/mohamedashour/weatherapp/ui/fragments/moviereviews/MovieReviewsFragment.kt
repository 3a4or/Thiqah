package com.example.mohamedashour.weatherapp.ui.fragments.moviereviews


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.data.models.MovieReviews
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_details.recyclerView

/**
 * A simple [Fragment] subclass.
 */
class MovieReviewsFragment : BaseFragment() , MovieReviewsContract.MovieReviewsView {

    lateinit var rootView: View
    private lateinit var presenter: MovieReviewsContract.MovieReviewsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie_reviews, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        presenter = MovieReviewsPresenter(this)
        recyclerView.layoutManager = GridLayoutManager(
            this.context,
            1,
            LinearLayoutManager.VERTICAL,
            false
        )
        presenter.getData(arguments!!.getString("id", ""))
    }

    override fun receiveData(list: List<MovieReviews.Result>) {
        recyclerView.adapter = ReviewsAdapter(list)
    }
}
