package com.company.teachers.ui.Fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup


import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.teachers.R
import com.company.teachers.Utilits.replaceFragment
import com.company.teachers.databinding.FragmentStudentsFilterBinding
import com.company.teachers.ui.Adapters.StudentRecyclerAdapter
import kotlinx.coroutines.launch
import android.view.ViewGroup.LayoutParams
import kotlinx.coroutines.runBlocking


class StudentsFilterFragment : Fragment() {

    private var _binding: FragmentStudentsFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentStudentsFilterBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        initFunc()
    }

    private fun initFunc() {
        var items = listOf("Subject 1", "Subject 2", "Subject 3", "Subject 4")
        var adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        var textField = binding.subjectsList
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        items = listOf("Group 1", "Group 2", "Group 3", "Group 4")
        adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        textField = binding.groupsList
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        items = listOf("Семестр 1", "Семестр 2")
        adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        textField = binding.semestersList
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.filterBtnNext.setOnClickListener{
            replaceFragment(StudentsListFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.main_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.main_menu_exit -> {
                replaceFragment(LoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}