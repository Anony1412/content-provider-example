package com.example.contentprovider_example.data.database

import android.database.sqlite.SQLiteDatabase

interface DatabaseContracts {
    interface View {
        fun onCreateSuccess(message: String)
        fun onUpgradeSuccess(message: String)
    }

    interface Presenter {
        fun handleCreate(db: SQLiteDatabase)
        fun handleUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    }
}
