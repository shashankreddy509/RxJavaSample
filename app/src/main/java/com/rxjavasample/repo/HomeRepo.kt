package com.rxjavasample.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rxjavasample.network.ApiClient
import com.rxjavasample.network.models.EmployeesList
import com.rxjavasample.utils.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class HomeRepo : Repo() {


    private val _employeeList = MutableLiveData<Result<EmployeesList>>()

    val employeeList: LiveData<Result<EmployeesList>> = _employeeList


    fun getAllEmployees() {
        disposables.add(
            ApiClient.client.getAllEmployee()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<EmployeesList>() {
                    override fun onSuccess(t: EmployeesList) {
                        _employeeList.postValue(Result.Success(t))
                    }

                    override fun onError(e: Throwable) {
                        _employeeList.postValue(Result.Failure(e))
                    }
                })
        )
    }
}