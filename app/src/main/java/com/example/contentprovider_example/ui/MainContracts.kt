package com.example.contentprovider_example.ui

import android.content.ContentResolver
import android.net.Uri
import com.example.contentprovider_example.data.model.Employee

interface MainContracts {
    interface View {
        fun showEmployees(employeeList: List<Employee>)
    }

    interface Presenter {
        fun handleInitializeData(contentResolver: ContentResolver, uri: Uri)
    }
}
