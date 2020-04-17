package com.example.contentprovider_example.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contentprovider_example.R
import com.example.contentprovider_example.data.model.Employee
import com.example.contentprovider_example.data.source.local.provider.EmployeeProvider
import com.example.contentprovider_example.ui.adapter.EmployeeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContracts.View {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this)
        // use main activity as other application to get data from content provider
        getDataFromProvider()
    }

    private fun getDataFromProvider() {
        val uri = Uri.parse(EmployeeProvider.URI)
        mainPresenter.handleInitializeData(contentResolver, uri)
    }

    override fun onDataInitializeSuccess(employeeList: ArrayList<Employee>) {
        recyclerViewEmployee.adapter = EmployeeAdapter(employeeList)
        recyclerViewEmployee.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewEmployee.setHasFixedSize(false)
    }
}
