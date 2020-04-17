package com.example.contentprovider_example.ui

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.contentprovider_example.R
import com.example.contentprovider_example.data.database.DatabaseHelper
import com.example.contentprovider_example.data.local.dao.DataDAO
import com.example.contentprovider_example.data.provider.EmployeeProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // use main activity as other application to get data from content provider
        getDataFromProvider()
    }

    private fun getDataFromProvider() {
        val contentResolver = contentResolver
        val uri = Uri.parse(EmployeeProvider.URI)
        val cursor = contentResolver.query(uri, null, null,
            null, null) as Cursor
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            Log.d("getDataFromProvider",
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_NAME)))
            cursor.moveToNext()
        }
    }
}
