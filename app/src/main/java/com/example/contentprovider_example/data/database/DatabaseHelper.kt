package com.example.contentprovider_example.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(private val context: Context)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), DatabaseContracts.View {

    private val dbPresenter = DatabasePresenter(this)

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            dbPresenter.handleCreate(db)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            dbPresenter.handleUpgrade(db, oldVersion, newVersion)
        }
    }

    override fun onCreateSuccess(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onUpgradeSuccess(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val DB_NAME = "MVP-ContentProvider-Example"
        var DB_VERSION = 1
        const val TABLE_NAME = "Employee"
        const val EMPLOYEE_ID = "_id"
        const val EMPLOYEE_NAME = "name"
    }
}
