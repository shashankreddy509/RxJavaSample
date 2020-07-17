package com.rxjavasample.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rxjavasample.R
import com.rxjavasample.network.models.Employees
import kotlinx.android.synthetic.main.layout_employee_list.view.*

class EmployeeAdapter(private val employeeData: ArrayList<Employees>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.layout_employee_list, parent, false)
        return VHEmployee(view)

    }

    override fun getItemCount(): Int {
        return employeeData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VHEmployee) {
            val presentEmployee = employeeData[position]
            holder.tvEmployeeName.text = "Name: ${presentEmployee.employee_name}"
            holder.tvEmployeeAge.text = "Age: ${presentEmployee.employee_age}"
            holder.tvEmployeeSal.text = "Salary: ${presentEmployee.employee_salary}"
        }

    }

    inner class VHEmployee(view: View) :
        RecyclerView.ViewHolder(view) {
        val tvEmployeeName = view.tvEmployeeName
        val tvEmployeeSal = view.tvEmployeeSal
        val tvEmployeeAge = view.tvEmployeeAge
    }

}