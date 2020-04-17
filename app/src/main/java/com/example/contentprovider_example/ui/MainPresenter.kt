package com.example.contentprovider_example.ui

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contentprovider_example.data.model.Employee
import com.example.contentprovider_example.data.source.local.database.DatabaseHelper
import com.example.contentprovider_example.ui.adapter.EmployeeAdapter

class MainPresenter(private val mainView: MainContracts.View, private val context: Context)
    : MainContracts.Presenter {

    override fun handleInitializeData(contentResolver: ContentResolver, uri: Uri): Cursor {
        val cursor = contentResolver.query(uri,
            null, null, null, null) as Cursor
        mainView.onDataInitializeSuccess("Get Data Success!")
        return cursor
    }

    override fun showData(
        cursor: Cursor,
        employeeList: ArrayList<Employee>,
        recyclerView: RecyclerView
    ) {
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val employee =
                Employee(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_NAME)))
            employeeList.add(employee)
            cursor.moveToNext()
        }
        recyclerView.adapter = EmployeeAdapter(employeeList)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(false)
    }
}
