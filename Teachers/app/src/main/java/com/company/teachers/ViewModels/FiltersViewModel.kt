package com.company.teachers.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.company.teachers.Retrofit.RetrofitApi
import com.company.teachers.dto.Group
import com.company.teachers.dto.Subject
import com.company.teachers.ui.Dialogs.LoadingDialog
import com.company.teachers.ui.Fragments.FiltersFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FiltersViewModel(application: Application, var filtersFragment: FiltersFragment) :
    AndroidViewModel(application) {
    var retrofitApi = RetrofitApi(filtersFragment)
    var semester = MutableLiveData("")
    var group = MutableLiveData("")
    var subject = MutableLiveData("")
    var examType = MutableLiveData("")
    var examTypes: MutableLiveData<List<String>> = MutableLiveData()
    var semesters: MutableLiveData<List<Int>> = MutableLiveData()
    var groups: MutableLiveData<List<Group>> = MutableLiveData()
    var subjects: MutableLiveData<List<Subject>> = MutableLiveData()


    var loadingDialog = filtersFragment.activity?.let { LoadingDialog(it) }

    init {
        GlobalScope.launch(Dispatchers.Main) {
            loadingDialog?.startLoading()

            try {
                val filtersResponse = retrofitApi.getFilters()
                if (filtersResponse != null) {
                    groups.value = filtersResponse.groups
                    subjects.value = filtersResponse.subjects

                    var examTypesList = listOf("Экзамен", "Зачет")
                    examTypes.value = examTypesList

                    var semestestrList = listOf(1, 2)
                    semesters.value = semestestrList
                }

                loadingDialog?.hideLoading()
            }
            catch (e: Exception){
                Log.i("filter", e.toString())
                loadingDialog?.hideLoading()
            }

        }
    }

}