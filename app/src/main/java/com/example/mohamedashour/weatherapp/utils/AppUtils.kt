package com.example.mohamedashour.weatherapp.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

object AppUtils {
    fun openActivity(from: Context, to: Class<*>, bundle: Bundle) {
        val intent = Intent(from, to)
        intent.putExtras(bundle)
        from.startActivity(intent)
    }
    fun openFragmentFromActivity(from: AppCompatActivity, to: Fragment, layout: Int, stackName: String) {
        from.supportFragmentManager.beginTransaction().replace(layout, to).addToBackStack(stackName).commit()
    }
}