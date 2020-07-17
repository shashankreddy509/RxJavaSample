package com.rxjavasample.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.rxjavasample.R
import com.rxjavasample.viewmodel.HomeViewModel
import com.rxjavasample.views.adapter.EmployeeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeViewModel.getAllEmployees()

        homeViewModel.employeeList.observe(this@MainActivity, Observer {
            when (it) {
                is com.rxjavasample.utils.Result.Success -> {
                    tvLoadingText.visibility = View.GONE
                    rvEmployeeList.visibility = View.VISIBLE
                    rvEmployeeList.adapter = EmployeeAdapter(it.data.data)
                }
                is com.rxjavasample.utils.Result.Failure -> {
                    Log.e("Error", it.throwable.toString())
                    tvLoadingText.visibility = View.VISIBLE
                    tvLoadingText.text = "Error loading Data"
                    rvEmployeeList.visibility = View.GONE
                }
            }
        })
    }
}