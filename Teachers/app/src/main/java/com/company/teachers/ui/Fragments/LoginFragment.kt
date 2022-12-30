package com.company.teachers.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.teachers.Authentification
import com.company.teachers.R
import com.company.teachers.Utilits.replaceFragment
import com.company.teachers.Utilits.showToast
import com.company.teachers.databinding.FragmentLoginBinding
import com.company.teachers.ui.Dialogs.LoadingDialog
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.concurrent.timer


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var mBinding: FragmentLoginBinding
    private lateinit var authentification: Authentification
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentLoginBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFunc()

        val loginButton = mBinding.loginLoginButton
        val forgotPasswordButton = mBinding.loginForgotPasswordBtn

        val loginText = mBinding.loginLoginInputText
        val passwordText = mBinding.loginPasswordInputText

        forgotPasswordButton.setOnClickListener {
            replaceFragment(ForgotPasswordFragment())
        }
        loginButton.setOnClickListener { login(loginText, passwordText) }

        //replaceFragment(StudentsFilterFragment())
    }

    private fun initFunc() {
        authentification = Authentification.getInstance()
        loadingDialog = LoadingDialog(activity)
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    private fun login(loginText: TextInputEditText?, passwordText: TextInputEditText?) {
        var login: String = loginText?.text.toString()
        var password: String = passwordText?.text.toString()

        if (login.isEmpty()) {
            showToast(getString(R.string.login_toast_enter_email))
        } else if (password.isEmpty()) {
            showToast(getString(R.string.login_toast_enter_password))
        } else {
            login = "vanya@gmail.ru"
            password = "12345"

            loadingDialog.startLoadingDialog()
            scope.launch {
                val loginAwait = async { authentification.login(login, password) }
                loginAwait.await()
                if (authentification.token !== null) {
                    loadingDialog.dismissLoadingDialog()
                    replaceFragment(StudentsFilterFragment())
                } else {
                    loadingDialog.dismissLoadingDialog()
                    showToast("Ошибка при входе!")
                }
            }
        }
    }
}