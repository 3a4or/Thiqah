package com.example.mohamedashour.weatherapp.ui.activities.base

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.ui.fragments.moviedetails.MovieDetailsFragment
import com.example.mohamedashour.weatherapp.ui.fragments.moviereviews.MovieReviewsFragment
import com.example.mohamedashour.weatherapp.utils.AppTools
import com.example.mohamedashour.weatherapp.utils.AppUtils

class BaseActivity : AppCompatActivity(), BaseContract.BaseView{

    private lateinit var progressDialog: ProgressDialog
    private lateinit var bundle: Bundle
    private lateinit var presenter: BaseContract.BasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initProgressDialog()
        presenter = BaseActivityPresenter(this)
        sendBundle()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun sendBundle() {
        bundle = intent.extras!!
        presenter.openFragmentPage(bundle.getString(AppTools.PAGE_TYPE, ""))
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
    }

    override fun setActionBarTitle(title: String) {
        supportActionBar!!.title = title
    }

    override fun showHideActionBar(visibility: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeActionBarColor() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setProgressBar(show: Boolean) {
        if (show) {
            progressDialog.show()
        } else{
            progressDialog.hide()
        }
    }

    override fun openVideoDetailsPage() {
        val fragment = MovieDetailsFragment()
        setActionBarTitle(bundle.getString("title", ""))
        fragment.arguments = bundle
        AppUtils.openFragmentFromActivity(this, fragment, R.id.fl_container, AppTools.STACK_NAME)
    }

    override fun openVideoReviewsPage() {
        val fragment = MovieReviewsFragment()
        setActionBarTitle(bundle.getString("title", ""))
        fragment.arguments = bundle
        AppUtils.openFragmentFromActivity(this, fragment, R.id.fl_container, AppTools.STACK_NAME)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
