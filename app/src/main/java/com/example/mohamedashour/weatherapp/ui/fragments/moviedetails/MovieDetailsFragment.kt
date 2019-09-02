package com.example.mohamedashour.weatherapp.ui.fragments.moviedetails


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.data.models.MovieDetails
import com.example.mohamedashour.weatherapp.ui.App
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.BaseFragment
import com.example.mohamedashour.weatherapp.utils.AppTools
import com.example.mohamedashour.weatherapp.utils.OnRecyclerClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailsFragment : BaseFragment(), MovieDetailsContract.MovieDetailsView{

    lateinit var rootView: View
    private lateinit var presenter: MovieDetailsContract.MovieDetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie_details, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        * I put the init() method here because The problem with fragments is that the view can be recreated but the fragment instance will be kept alive.
        *  What happens then? This means that the views inside the cache would be no longer valid.
        */
        init()
        clickListener()
    }

    private fun init() {
        presenter = MovieDetailsPresenter(this)
        date_text.text = arguments!!.getString("date", "")
        rating_text.text = arguments!!.getString("rate", "")
        desc_text.text = arguments!!.getString("desc", "")
        Picasso.get().load("https://image.tmdb.org/t/p/w342" + arguments!!.getString("image", "")).error(R.mipmap.ic_launcher)
            .into(image_movie)
        recyclerView.layoutManager = GridLayoutManager(this.context, 1, LinearLayoutManager.VERTICAL, false)
        presenter.getData(arguments!!.getString("id", ""))
    }

    private fun clickListener() {
        review_btn.setOnClickListener {
            arguments!!.putString(AppTools.PAGE_TYPE, AppTools.MOVIES_REVIEWS)
            openBaseActivity(arguments!!)
        }
        fav_btn.setOnClickListener {
            presenter.addFavourite(arguments!!.getString("id", ""), arguments!!.getString("title", ""),
                arguments!!.getString("date", ""), arguments!!.getString("rate", ""),
                arguments!!.getString("desc", ""), arguments!!.getString("image", ""))
        }
    }

    override fun receiveData(list: List<MovieDetails.Result>) {
        recyclerView.adapter = TrailersAdapter(this.context!!, list, object : OnRecyclerClick {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://m.youtube.com/watch?v=" + list[position].key)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        })
    }

    override fun addedSuccessfully() {
        showToastMessage(App.instance().resources.getString(R.string.msg_done))
    }
}
