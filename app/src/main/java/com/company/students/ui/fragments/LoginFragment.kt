package com.company.students.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.company.students.viewModels.AuthenticationViewModel
import com.company.students.R
import com.company.students.databinding.FragmentLoginBinding
import com.company.students.viewModels.AuthenticationViewModelFactory

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var _binding: FragmentLoginBinding
    private lateinit var authenticationViewModel: AuthenticationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authenticationViewModel = ViewModelProvider(
            this,
            AuthenticationViewModelFactory(application = Application(), this)
        ).get(AuthenticationViewModel::class.java)

        _binding.viewModel = authenticationViewModel

    }
}