package com.example.contentprovider_example.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contentprovider_example.R
import com.example.contentprovider_example.data.model.Employee
import com.example.contentprovider_example.data.source.local.provider.EmployeeProvider
import com.example.contentprovider_example.ui.adapter.EmployeeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContracts.View {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var employeeList: ArrayList<Employee>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this)
        // use main activity as other application to get data from content provider
        getDataFromProvider()
        initView()
    }

    private fun getDataFromProvider() {
        val uri = Uri.parse(EmployeeProvider.URI)
        employeeList = ArrayList()
        // get data from content provider set for data list
        mainPresenter.handleInitializeData(contentResolver, uri, employeeList)
    }

    private fun initView() {
        recyclerViewEmployee.adapter = EmployeeAdapter(employeeList)
        recyclerViewEmployee.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewEmployee.setHasFixedSize(false)
    }

    override fun onDataInitializeSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
