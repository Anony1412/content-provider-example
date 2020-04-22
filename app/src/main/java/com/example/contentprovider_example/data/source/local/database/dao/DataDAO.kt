package com.example.contentprovider_example.data.source.local.database.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.contentprovider_example.data.source.local.database.DatabaseHelper
import com.example.contentprovider_example.data.model.Employee

class DataDAO(private val databaseHelper: DatabaseHelper) {
    private var database: SQLiteDatabase? = null

    fun openDatabase() {
        database = databaseHelper.writableDatabase
        Log.d("openDatabase", "abc")
    }

    fun closeDatabase() {
        databaseHelper.close()
    }

    fun getDataProvider(
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        return database!!.query(
            DatabaseHelper.TABLE_NAME, projection,
            selection, selectionArgs, null, null, sortOrder)
    }

    fun insert(employee: Employee) {
        val contentValues = ContentValues().apply {
            put(DatabaseHelper.EMPLOYEE_NAME, employee.name)
        }
        database!!.insert(DatabaseHelper.TABLE_NAME, null, contentValues)
    }
}
