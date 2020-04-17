package com.example.contentprovider_example.data.local.dao

import com.example.contentprovider_example.data.database.DatabaseHelper

interface DataDAOContracts {
    interface Presenter {
        fun handleOpenDatabase(databaseHelper: DatabaseHelper)
        fun handleCloseDatabase(databaseHelper: DatabaseHelper)
    }
}
