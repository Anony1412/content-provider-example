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

class MainPresenter(private val mainView: MainContracts.View)
    : MainContracts.Presenter {

    override fun handleInitializeData (
        contentResolver: ContentResolver,
        uri: Uri,
        employeeList: ArrayList<Employee>) {
        val cursor = contentResolver.query(uri,
            null, null, null, null) as Cursor
        mainView.onDataInitializeSuccess("Get Data Success!")
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val employee =
                Employee(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_NAME)))
            employeeList.add(employee)
            cursor.moveToNext()
        }
    }
}
