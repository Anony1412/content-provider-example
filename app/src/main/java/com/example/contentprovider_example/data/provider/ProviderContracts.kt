package com.example.contentprovider_example.data.provider

import android.content.UriMatcher
import android.database.Cursor
import com.example.contentprovider_example.data.database.DatabaseHelper
import com.example.contentprovider_example.data.local.dao.DataDAO

interface ProviderContracts {

    interface Presenter {
        fun handleCreate(uriMatcher: UriMatcher)
        fun initDatabase(databaseHelper: DatabaseHelper, dao: DataDAO)
        fun initUri(uriMatcher: UriMatcher)
        fun getDataProvider(dao: DataDAO, projection: Array<out String>?, selection: String?,
                            selectionArgs: Array<out String>?, sortOrder: String?): Cursor
    }
}
