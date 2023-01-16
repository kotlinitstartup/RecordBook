package com.company.students.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FiltersViewModelFactory(
    val application: Application,
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FiltersViewModel::class.java)) {
            return FiltersViewModel(application) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}