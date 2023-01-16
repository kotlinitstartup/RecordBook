package com.company.students.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.students.ui.fragments.LoginFragment
import com.company.students.ui.fragments.MarksListFragment

class MarksViewModelFactory(
    val application: Application,
    val marksListFragment: MarksListFragment
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarksViewModel::class.java)) {
            return MarksViewModel(application, marksListFragment) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}