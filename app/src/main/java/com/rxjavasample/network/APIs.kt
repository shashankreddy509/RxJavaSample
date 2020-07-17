package com.rxjavasample.network

import com.rxjavasample.network.models.EmployeesList
import io.reactivex.Single
import retrofit2.http.GET

interface APIs {

    @GET("employees")
    fun getAllEmployee(): Single<EmployeesList>

}