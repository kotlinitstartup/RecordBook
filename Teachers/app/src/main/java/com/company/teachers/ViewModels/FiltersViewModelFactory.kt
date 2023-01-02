package com.company.teachers.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.teachers.ui.Fragments.FiltersFragment
import com.company.teachers.ui.Fragments.LoginFragment

class FiltersViewModelFactory (
    val application: Application,
    val filtersFragment: FiltersFragment
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FiltersViewModel::class.java)) {
            return FiltersViewModel(application, filtersFragment) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}