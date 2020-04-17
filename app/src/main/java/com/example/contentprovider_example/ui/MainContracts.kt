package com.example.contentprovider_example.ui

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.contentprovider_example.data.model.Employee

interface MainContracts {
    interface View {
        fun onDataInitializeSuccess(message: String)
    }

    interface Presenter {
        fun handleInitializeData(contentResolver: ContentResolver,
                                 uri: Uri, employeeList: ArrayList<Employee>)
    }
}
