package com.example.contentprovider_example.data.database

import android.database.sqlite.SQLiteDatabase

class DatabasePresenter(private val databaseView: DatabaseContracts.View)
    : DatabaseContracts.Presenter {

    override fun handleCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE ${DatabaseHelper.TABLE_NAME}" +
                " ( ${DatabaseHelper.EMPLOYEE_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${DatabaseHelper.EMPLOYEE_NAME} TEXT );"
        db.execSQL(query)
        databaseView.onCreateSuccess("Create Database Success!")
    }

    override fun handleUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS ${DatabaseHelper.TABLE_NAME};"
        db.execSQL(query)
        handleCreate(db)
        databaseView.onUpgradeSuccess("Upgrade Database Success!")
    }
}
