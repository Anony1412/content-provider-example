package com.example.contentprovider_example.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.contentprovider_example.data.database.DatabaseHelper
import com.example.contentprovider_example.data.local.dao.DataDAO

class EmployeeProvider : ContentProvider(){

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var databaseDAO: DataDAO

    private val providerPresenter = ProviderPresenter()

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
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
                cursor = handleQuery(projection, selection, selectionArgs, sortOrder)
            }
        }
        return cursor
    }

    private fun handleQuery(projection: Array<out String>?, selection: String?,
                            selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        return providerPresenter.getDataProvider(databaseDAO, projection, selection,
            selectionArgs, sortOrder)
    }

    override fun onCreate(): Boolean {
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        databaseHelper = context?.let { DatabaseHelper(it) }!!
        databaseDAO = DataDAO(databaseHelper)
        providerPresenter.handleCreate(uriMatcher)
        providerPresenter.initDatabase(databaseHelper, databaseDAO)
        return false
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
        const val AUTHORITY = "com.example.contentprovider_example.data.provider.EmployeeProvider"
        const val DATA_TYPE = "EMPLOYEE"
        val URI = "content://$AUTHORITY/$DATA_TYPE"
        const val EMPLOYEE_TABLE_CODE = 1
    }
}
