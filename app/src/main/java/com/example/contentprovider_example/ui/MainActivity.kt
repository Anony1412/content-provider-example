package com.example.contentprovider_example.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contentprovider_example.R
import com.example.contentprovider_example.data.model.Employee
import com.example.contentprovider_example.data.source.local.provider.EmployeeProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContracts.View {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var employeeList: ArrayList<Employee>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this, this)
        // use main activity as other application to get data from content provider
        getDataFromProvider()
    }

    private fun getDataFromProvider() {
        val uri = Uri.parse(EmployeeProvider.URI)
        val cursor = mainPresenter.handleInitializeData(contentResolver, uri)
        employeeList = ArrayList()
        mainPresenter.showData(cursor, employeeList, recyclerViewEmployee)
    }

    override fun onDataInitializeSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
