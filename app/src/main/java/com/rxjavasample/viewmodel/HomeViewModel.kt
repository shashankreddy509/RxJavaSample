package com.rxjavasample.viewmodel

import androidx.lifecycle.ViewModel
import com.rxjavasample.repo.HomeRepo

class HomeViewModel : ViewModel() {

    private val repo = HomeRepo()

    val employeeList = repo.employeeList

    fun getAllEmployees() = repo.getAllEmployees()

}