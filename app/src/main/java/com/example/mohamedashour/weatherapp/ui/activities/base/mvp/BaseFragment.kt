package com.example.mohamedashour.weatherapp.ui.activities.base.mvp

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Toast
import com.example.mohamedashour.weatherapp.ui.activities.base.BaseActivity
import com.example.mohamedashour.weatherapp.ui.activities.main.MainActivity
import com.example.mohamedashour.weatherapp.utils.AppUtils

abstract class BaseFragment : Fragment(), IBaseView{

    private lateinit var progressDialog: ProgressDialog
    /*
    * Normally, properties declared as having a non-null type must be initialized in the constructor.
     * However, fairly often this is not convenient. For example, properties can be initialized through dependency injection,
      * or in the setup method of a unit test. In this case, you cannot supply a non-null initializer in the constructor,
      * but you still want to avoid null checks when referencing the property inside the body of a class.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProgressDialog()
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this.context, ProgressDialog.THEME_HOLO_LIGHT)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun setProgressBar(show: Boolean) {
        if (show) {
            progressDialog.show()
        } else{
            progressDialog.hide()
        }
    }

    protected fun openBaseActivity(bundle: Bundle) {
        AppUtils.openActivity(this.context!!, BaseActivity::class.java, bundle)
    }
}