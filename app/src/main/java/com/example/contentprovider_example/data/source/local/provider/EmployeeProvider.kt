package com.example.contentprovider_example.data.source.local.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.contentprovider_example.data.model.Employee
import com.example.contentprovider_example.data.source.local.database.DatabaseHelper
import com.example.contentprovider_example.data.source.local.database.dao.DataDAO

class EmployeeProvider : ContentProvider(){

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var databaseDAO: DataDAO

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor: Cursor? = null
        when (uriMatcher.match(uri)) {
            EMPLOYEE_TABLE_CODE -> {
                cursor = databaseDAO.getDataProvider(projection, selection, selectionArgs, sortOrder)
            }
        }
        return cursor
    }

    override fun onCreate(): Boolean {
        /** create uri */
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        //content://com.example.contentprovider_example.data.source.local.provider.EmployeeProvider/EMPLOYEE
        uriMatcher.addURI(AUTHORITY, DATA_TYPE, EMPLOYEE_TABLE_CODE)

        /** init database */
        initDatabase()
        return false
    }

    private fun initDatabase() {
        databaseHelper = context?.let { DatabaseHelper(it) }!!
        databaseDAO = DataDAO(databaseHelper)
        databaseDAO.openDatabase()
        for (i in 0 until 10) {
            val employee = Employee(i, "test $i")
            databaseDAO.insert(employee)
        }
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    companion object {
        lateinit var uriMatcher: UriMatcher
        const val AUTHORITY = "com.example.contentprovider_example.data.source.local.provider.EmployeeProvider"
        const val DATA_TYPE = "EMPLOYEE"
        val URI = "content://$AUTHORITY/$DATA_TYPE"
        const val EMPLOYEE_TABLE_CODE = 1
    }
}
