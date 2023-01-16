package com.company.students.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup


import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.company.students.R
import com.company.students.databinding.FiltersFragmentBinding
import com.company.students.utils.removeFromSharedPreferences
import com.company.students.utils.replaceFragment
import com.company.students.utils.showToast
import com.company.students.viewModels.FiltersViewModel
import com.company.students.viewModels.FiltersViewModelFactory


class FiltersFragment : Fragment(R.layout.filters_fragment) {

    private lateinit var _binding: FiltersFragmentBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.filters_fragment, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        var filtersViewModel = ViewModelProvider(
            this,
            FiltersViewModelFactory(application = Application())
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

        binding.viewmodel?.types?.observe(viewLifecycleOwner, Observer {
            binding.typesList.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.select_dialog_item,
                    it
                )
            )
        })

        binding.filterBtnNext.setOnClickListener {
            val viewModel = binding.viewmodel as FiltersViewModel
            if (viewModel.type.value.toString().isEmpty() ||
                viewModel.semester.value.toString().isEmpty()
            ) {
                this.showToast("Выберте все параметры!")
            } else {
                val args = Bundle()
                args.putString("type", viewModel.type.value.toString())
                args.putString("semester", viewModel.semester.value.toString())
                val fragment = MarksListFragment()
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
                removeFromSharedPreferences("token")
                replaceFragment(LoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}