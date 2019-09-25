package com.example.mohamedashour.weatherapp.ui.fragments.postdetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_post_details.*

/**
 * A simple [Fragment] subclass.
 */
class PostDetailsFragment : BaseFragment(){

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_post_details, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        * I put the init() method here because The problem with fragments is that the view can be recreated but the fragment instance will be kept alive.
        *  What happens then? This means that the views inside the cache would be no longer valid.
        */
        init()
    }

    private fun init() {
        tv_title.text = arguments!!.getString("title", "")
        tv_details.text = arguments!!.getString("body", "")
    }
}
