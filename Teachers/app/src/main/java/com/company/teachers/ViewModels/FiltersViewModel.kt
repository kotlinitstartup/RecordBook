package com.company.teachers.viewModels

import android.R
import android.app.Application
import android.widget.ArrayAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.company.teachers.Retrofit.RetrofitApi
import com.company.teachers.dto.FiltersPayload
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
    var semesters: MutableLiveData<List<String>> = MutableLiveData()
    var groups: MutableLiveData<List<String>> = MutableLiveData()
    var subjects: MutableLiveData<List<String>> = MutableLiveData()


    var loadingDialog = filtersFragment.activity?.let { LoadingDialog(it) }

    init {
        GlobalScope.launch(Dispatchers.Main) {
            loadingDialog?.startLoading()

            val filtersResponse = retrofitApi.getFilters()

            if (filtersResponse != null) {
                groups.value = filtersResponse.groups.map { el -> el.name }
                subjects.value = filtersResponse.subjects.map { el -> el.name }


                var semestestrList = listOf("1", "2")
                semesters.value = semestestrList
            }

            loadingDialog?.hideLoading()
        }
    }

}