package com.company.teachers.ViewModels

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class MainFactory(val application: Application, val text: String):
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MainViewModel(application, text) as T
    }
}