package com.company.teachers.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.company.teachers.ui.Fragments.FiltersFragment
import com.company.teachers.ui.Fragments.StudentsListFragment

class StudentListViewModel(application: Application, var studentsListFragment: StudentsListFragment):
    AndroidViewModel(application) {

    }