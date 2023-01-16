package com.company.students.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.company.students.ui.fragments.MarksListFragment

class MarksViewModel(application: Application, var marksListFragment: MarksListFragment) :
    AndroidViewModel(application) {


}