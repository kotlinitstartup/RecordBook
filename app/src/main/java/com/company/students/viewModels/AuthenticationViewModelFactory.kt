package com.company.students.viewModels


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.students.ui.fragments.LoginFragment

class AuthenticationViewModelFactory(
    val application: Application,
    val loginFragment: LoginFragment
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthenticationViewModel::class.java)) {
            return AuthenticationViewModel(application, loginFragment) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}