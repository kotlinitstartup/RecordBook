package com.company.students.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class FiltersViewModel(application: Application) :
    AndroidViewModel(application) {
    var semester = MutableLiveData("")
    var type = MutableLiveData("")

    var semesters: MutableLiveData<List<String>> = MutableLiveData()
    var types: MutableLiveData<List<String>> = MutableLiveData()

    init {
        var semestersList = listOf("1", "2")
        semesters.value = semestersList

        var typesList = listOf("Экзамен", "Зачёт")
        types.value = typesList
    }

}