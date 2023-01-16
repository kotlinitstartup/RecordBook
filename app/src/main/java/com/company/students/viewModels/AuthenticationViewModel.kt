package com.company.students.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.company.students.retrofit.RetrofitApi
import com.company.students.ui.dialogs.LoadingDialog
import com.company.students.ui.fragments.FiltersFragment
import com.company.students.ui.fragments.LoginFragment
import com.company.students.utils.getFromSharedPreferences
import com.company.students.utils.replaceFragment
import com.company.students.utils.saveToSharedPreferences
import com.company.students.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthenticationViewModel(application: Application, var loginFragment: LoginFragment) :
    AndroidViewModel(application) {
    var recordBookNumber = MutableLiveData("")
    private val retrofitApi: RetrofitApi = RetrofitApi(loginFragment)

    var loadingDialog = loginFragment.activity?.let { LoadingDialog(it) }

    fun login() {
        if (recordBookNumber.value.toString().length !== 10) {
            loginFragment.showToast("Длина номера зачетки должна быть 10 символов")
            return
        }

        GlobalScope.launch(Dispatchers.Main) {
            try {
                loadingDialog?.startLoading()

                val studentLoginResponse = retrofitApi.login(recordBookNumber.value.toString())

                val tokenKey = "token"
                if (studentLoginResponse?.token !== null) {
                    loginFragment.saveToSharedPreferences(
                        tokenKey,
                        studentLoginResponse?.token
                    )
                }

                loadingDialog?.hideLoading()

                val token = loginFragment.getFromSharedPreferences(tokenKey)

                if (token !== null) {
                    loginFragment.replaceFragment(FiltersFragment())
                } else {
                    loginFragment.showToast("Ошибка авторизации")
                }
            } catch (e: Exception) {
                loadingDialog?.hideLoading()
                loginFragment.showToast("Ошибка при авторизации")
            }
        }
    }
}