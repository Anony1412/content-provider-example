package com.example.contentprovider_example.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contentprovider_example.R
import com.example.contentprovider_example.data.model.Employee
import kotlinx.android.synthetic.main.employee_item.view.*

class EmployeeAdapter(
    private val employeeList: List<Employee>
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(employee: Employee) {
            itemView.textViewItemName.text = employee.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun getItemCount(): Int = employeeList.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employeeList[position])
    }
}
