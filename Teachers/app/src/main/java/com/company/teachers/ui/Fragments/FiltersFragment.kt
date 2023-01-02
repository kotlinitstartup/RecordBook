package com.company.teachers.ui.Fragments

import android.app.Application
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup


import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.company.teachers.R
import com.company.teachers.utils.replaceFragment
import com.company.teachers.databinding.FragmentStudentsFilterBinding
import com.company.teachers.viewModels.AuthenticationViewModel
import com.company.teachers.viewModels.AuthenticationViewModelFactory
import com.company.teachers.viewModels.FiltersViewModel
import com.company.teachers.viewModels.FiltersViewModelFactory


class FiltersFragment : Fragment(R.layout.fragment_students_filter) {

    private lateinit var _binding: FragmentStudentsFilterBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_students_filter, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        var filtersViewModel = ViewModelProvider(
            this,
            FiltersViewModelFactory(application = Application(), this)
        ).get(FiltersViewModel::class.java)

        binding.viewmodel = filtersViewModel

        binding.viewmodel?.semesters?.observe(viewLifecycleOwner, Observer {
            binding.semestersList.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.activity_list_item,
                    it
                )
            )
        })

        binding.viewmodel?.groups?.observe(viewLifecycleOwner, Observer {
            binding.groupsList.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.activity_list_item,
                    it
                )
            )
        })

        binding.viewmodel?.subjects?.observe(viewLifecycleOwner, Observer {
            binding.subjectsList.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.select_dialog_item,
                    it
                )
            )
        })

        binding.filterBtnNext.setOnClickListener {
            replaceFragment(StudentsListFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.main_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_menu_exit -> {
                replaceFragment(LoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}