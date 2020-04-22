package com.example.contentprovider_example.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(
    context: Context
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE ${DatabaseHelper.TABLE_NAME}" +
                " ( ${DatabaseHelper.EMPLOYEE_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${DatabaseHelper.EMPLOYEE_NAME} TEXT );"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS ${DatabaseHelper.TABLE_NAME};"
        db?.execSQL(query)
        onCreate(db)
    }

    companion object {
        const val DB_NAME = "MVP-ContentProvider-Example"
        const val DB_VERSION = 1
        const val TABLE_NAME = "Employee"
        const val EMPLOYEE_ID = "_id"
        const val EMPLOYEE_NAME = "name"
    }
}
