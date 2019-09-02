package com.example.mohamedashour.weatherapp.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.ui.fragments.home.HomeFragment
import com.example.mohamedashour.weatherapp.utils.AppTools
import com.example.mohamedashour.weatherapp.utils.AppUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)
        loadFragment(HomeFragment.newInstance(), null)
    }

    companion object {

        private lateinit var instance : MainActivity

        fun getInstancem() : MainActivity {

            return instance
        }
    }

    private fun loadFragment(fragment: Fragment, bundle: Bundle?) {
        if (bundle != null) {
            fragment.arguments = bundle
        }
        AppUtils.openFragmentFromActivity(this, fragment, R.id.fl_container, AppTools.STACK_NAME)
    }

    override fun showToastMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProgressBar(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
