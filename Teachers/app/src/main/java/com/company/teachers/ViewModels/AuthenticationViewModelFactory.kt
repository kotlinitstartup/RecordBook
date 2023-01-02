package com.company.teachers.viewModels

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.teachers.ui.Fragments.LoginFragment

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