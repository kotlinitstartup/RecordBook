package com.company.teachers.ui.Fragments

import android.widget.TextView
import androidx.fragment.app.Fragment
import com.company.teachers.Authentification
import com.company.teachers.R
import com.company.teachers.Utilits.replaceFragment
import com.company.teachers.Utilits.showToast
import com.company.teachers.databinding.FragmentLoginBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var mBinding: FragmentLoginBinding
    private lateinit var authentification: Authentification

    override fun onResume() {
        super.onResume()
        initFunc()

        mBinding = FragmentLoginBinding.inflate(layoutInflater)


        var loginButton = view?.findViewById<MaterialButton>(R.id.login_login_button)
        var forgotPasswordButton = view?.findViewById<TextView>(R.id.login_forgot_password_btn)

        var loginText = view?.findViewById<TextInputEditText>(R.id.login_login_input_text)
        var passwordText = view?.findViewById<TextInputEditText>(R.id.login_password_input_text)

        forgotPasswordButton?.setOnClickListener {
            replaceFragment(ForgotPasswordFragment())
        }
        loginButton?.setOnClickListener { Login(loginText, passwordText) }
    }


    private fun initFunc() {
        authentification = Authentification.getInstance()
    }

    private fun Login(loginText: TextInputEditText?, passwordText: TextInputEditText?) {
        var login: String = loginText?.text.toString()
        var password: String = passwordText?.text.toString()

        if (login.isEmpty()) {
            showToast(getString(R.string.login_toast_enter_email))
        } else if (password.isEmpty()) {
            showToast(getString(R.string.login_toast_enter_password))
        } else {
            replaceFragment(StudentsFilterFragment())

//            authentification.Login(login, password)
//
//            if (authentification.currentTeacher != null){
//                replaceFragment(StudentsFilterFragment())
//            }
//            else {
//                showToast("Не удалось войти")
//            }

        }

    }

}