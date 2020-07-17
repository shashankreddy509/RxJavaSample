package com.rxjavasample.network.models

data class EmployeesList(val status: Boolean, val data: ArrayList<Employees>)

data class Employees(
    val id: String,
    val employee_name: String,
    val employee_salary: String,
    val employee_age: String
)