package com.example.contentprovider_example.data.provider

import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.contentprovider_example.data.database.DatabaseHelper
import com.example.contentprovider_example.data.local.Employee
import com.example.contentprovider_example.data.local.dao.DataDAO

class ProviderPresenter : ProviderContracts.Presenter {

    override fun handleCreate(uriMatcher: UriMatcher) {
        initUri(uriMatcher)
    }

    override fun initDatabase(databaseHelper: DatabaseHelper, dao: DataDAO) {
        dao.openDatabase()
        for (i in 0 until 10) {
            val employee = Employee(i, "test $i")
            dao.insert(employee)
        }
    }

    override fun initUri(uriMatcher: UriMatcher) {
        //content://com.example.contentprovider_example.data.provider.EmployeeProvider/EMPLOYEE
        uriMatcher.addURI(EmployeeProvider.AUTHORITY, EmployeeProvider.DATA_TYPE,
            EmployeeProvider.EMPLOYEE_TABLE_CODE)
    }

    override fun getDataProvider(
        dao: DataDAO,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        return dao.getDataProvider(projection, selection, selectionArgs, sortOrder)
    }
}
