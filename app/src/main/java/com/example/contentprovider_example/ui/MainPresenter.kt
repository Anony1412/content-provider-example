package com.example.contentprovider_example.ui

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import com.example.contentprovider_example.data.model.Employee
import com.example.contentprovider_example.data.source.local.database.DatabaseHelper

class MainPresenter(private val mainView: MainContracts.View) : MainContracts.Presenter {

    override fun handleInitializeData (contentResolver: ContentResolver, uri: Uri) {
        val employeeList: ArrayList<Employee> = ArrayList()
        val cursor = contentResolver.query(uri,
            null, null, null, null) as Cursor
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val employee =
                Employee(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_NAME)))
            employeeList.add(employee)
            cursor.moveToNext()
        }
        mainView.showEmployees(employeeList)
    }
}
