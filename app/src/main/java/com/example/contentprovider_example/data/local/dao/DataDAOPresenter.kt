package com.example.contentprovider_example.data.local.dao

import android.database.sqlite.SQLiteDatabase
import com.example.contentprovider_example.data.database.DatabaseHelper

class DataDAOPresenter(private var database: SQLiteDatabase): DataDAOContracts.Presenter {

    override fun handleOpenDatabase(databaseHelper: DatabaseHelper) {
        database = databaseHelper.writableDatabase
    }

    override fun handleCloseDatabase(databaseHelper: DatabaseHelper) {
        databaseHelper.close()
    }
}
