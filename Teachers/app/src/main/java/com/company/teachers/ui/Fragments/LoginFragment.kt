package com.company.teachers.ui.Fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.company.teachers.viewModels.AuthenticationViewModel
import com.company.teachers.R
import com.company.teachers.utils.replaceFragment
import com.company.teachers.databinding.FragmentLoginBinding
import com.company.teachers.viewModels.AuthenticationViewModelFactory
import com.company.teachers.viewModels.MainFactory
import com.company.teachers.viewModels.MainViewModel


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var mBinding: FragmentLoginBinding
    private lateinit var authenticationViewModel: AuthenticationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authenticationViewModel = ViewModelProvider(
            this,
            AuthenticationViewModelFactory(application = Application(), this)
        ).get(AuthenticationViewModel::class.java)
        mBinding.viewModel = authenticationViewModel

        val forgotPasswordButton = mBinding.loginForgotPasswordBtn

        forgotPasswordButton.setOnClickListener {
            replaceFragment(ForgotPasswordFragment())
        }
    }


}