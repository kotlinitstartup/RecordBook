package com.company.teachers.ui.Fragments

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContentInfo
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup


import android.widget.ArrayAdapter
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.teachers.R
import com.company.teachers.Retrofit.RetrofitApi
import com.company.teachers.databinding.FragmentStudentsFilterBinding
import com.company.teachers.dto.*
import com.company.teachers.ui.Dialogs.LoadingDialog
import com.company.teachers.utils.*
import com.company.teachers.viewModels.FiltersViewModel
import com.company.teachers.viewModels.FiltersViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FiltersFragment : Fragment(R.layout.fragment_students_filter) {


    var loadingDialog = this.activity?.let { LoadingDialog(it) }
    private lateinit var _binding: FragmentStudentsFilterBinding
    private val binding get() = _binding
    lateinit var filtersViewModel: FiltersViewModel

    var localGroups: List<Group> = ArrayList()
    var localSubjects: List<Subject> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_students_filter, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        filtersViewModel = ViewModelProvider(
            this,
            FiltersViewModelFactory(application = Application(), this)
        ).get(FiltersViewModel::class.java)

        binding.viewmodel = filtersViewModel

        binding.viewmodel?.semesters?.observe(viewLifecycleOwner, Observer {
            binding.semestersList.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.select_dialog_item,
                    it
                )
            )
        })


        binding.viewmodel?.groups?.observe(viewLifecycleOwner, Observer {
            localGroups = it
            binding.groupsList.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.select_dialog_item,
                    it
                )
            )
        })

        binding.viewmodel?.subjects?.observe(viewLifecycleOwner, Observer {
            localSubjects = it
            binding.subjectsList.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.select_dialog_item,
                    it
                )
            )
        })

        binding.viewmodel?.examTypes?.observe(viewLifecycleOwner, Observer {
            binding.examTypesList.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.select_dialog_item,
                    it
                )
            )
        })

        binding.filterBtnNext.setOnClickListener {
            val args = Bundle()

            var groupId =
                localGroups.find { it.name == filtersViewModel.group.value?.toString() }?.id

            var subjectId =
                localSubjects.find { it.name == filtersViewModel.subject.value?.toString() }?.id

            if (binding.groupsList.length() < 1 ||
                binding.subjectsList.length() < 1 ||
                binding.semestersList.length() < 1 ||
                binding.examTypesList.length() < 1) {
                showToast("Заполните все поля!")
            } else {
                args.putString("groupId", groupId.toString())
                args.putString("subjectId", subjectId.toString())
                args.putString("examType", filtersViewModel.examType.value.toString())
                args.putString("semesterId", filtersViewModel.semester.value.toString())

                val fragment = StudentsListFragment()
                fragment.arguments = args
                replaceFragment(fragment)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.main_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_menu_exit -> {
                this.removeFromSharedPreferences("token")
                replaceFragment(LoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}