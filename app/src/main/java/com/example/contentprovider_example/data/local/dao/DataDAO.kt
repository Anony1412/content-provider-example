package com.example.contentprovider_example.data.local.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.Toast
import com.example.contentprovider_example.data.database.DatabaseHelper
import com.example.contentprovider_example.data.local.Employee

class DataDAO(private val databaseHelper: DatabaseHelper) {
    private lateinit var database: SQLiteDatabase
//    private val daoPresenter = database?.let { DataDAOPresenter(it) }

    fun openDatabase() {
//        daoPresenter?.handleOpenDatabase(databaseHelper)
        database = databaseHelper.writableDatabase
        Log.d("openDatabase", "abc")
    }

    fun closeDatabase() {
//        daoPresenter?.handleCloseDatabase(databaseHelper)
        databaseHelper.close()
    }

    fun getDataProvider(
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        return database.query(DatabaseHelper.TABLE_NAME, projection,
            selection, selectionArgs, null, null, sortOrder)
    }

    fun insert(employee: Employee) {
        val contentValues = ContentValues().apply {
            put(DatabaseHelper.EMPLOYEE_NAME, employee.name)
        }
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues)
    }
}
