package com.company.teachers.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.company.teachers.Retrofit.RetrofitApi
import com.company.teachers.dto.TeacherLoginPayload
import com.company.teachers.ui.Dialogs.LoadingDialog
import com.company.teachers.ui.Fragments.LoginFragment
import com.company.teachers.ui.Fragments.FiltersFragment
import com.company.teachers.utils.getFromSharedPreferences
import com.company.teachers.utils.replaceFragment
import com.company.teachers.utils.saveToSharedPreferences
import com.company.teachers.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthenticationViewModel(application: Application, var loginFragment: LoginFragment) :
    AndroidViewModel(application) {

    var retrofitApi = RetrofitApi(loginFragment)
    var token: String? = null
    var loginText = "vanya@gmail.ru"
    var passwordText = "12345"
    var loadingDialog = loginFragment.activity?.let { LoadingDialog(it) }

    fun login() {

            if (loginText.isEmpty()) {
                loginFragment.showToast("Введите почту")
            }
            if (passwordText.isEmpty()) {
                loginFragment.showToast("Введите пароль")
            }

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    loadingDialog?.startLoading()

                    val teacherLoginPayload = TeacherLoginPayload(loginText, passwordText)
                    val teacherLoginResponse = retrofitApi.login(teacherLoginPayload)
                    teacherLoginResponse?.token?.let {
                        loginFragment.saveToSharedPreferences(
                            "token",
                            it
                        )
                    }

                    loadingDialog?.hideLoading()

                    if (loginFragment.getFromSharedPreferences("token") !== null){
                        loginFragment.replaceFragment(FiltersFragment())
                    }
                    else loginFragment.showToast("Ошибка авторизации")
                } catch (e: Exception) {
                    loadingDialog?.hideLoading()
                    loginFragment.showToast("Ошибка при авторизации")
                }

            }
    }

    fun signOut() {
        token = null
    }

}