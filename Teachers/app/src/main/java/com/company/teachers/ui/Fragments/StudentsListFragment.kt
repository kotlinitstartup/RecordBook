package com.company.teachers.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.teachers.databinding.FragmentStudentsListBinding
import com.company.teachers.dto.Student
import com.company.teachers.R
import com.company.teachers.databinding.FragmentStudentsFilterBinding
import com.company.teachers.ui.Adapters.StudentRecyclerAdapter


class StudentsListFragment : BaseFragment(R.layout.fragment_students_list) {

    private var _binding: FragmentStudentsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val student = Student(1, "John", "London")

        _binding = FragmentStudentsListBinding.inflate(inflater,container,false)

        return binding.root
    }

    private fun fillList(): List<Student> {
        val data = mutableListOf<Student>()
        (0..30).forEach { i -> data.add(Student(i,"Name", "Surname")) }
        return data
    }

    private fun getMarksList(): List<String> {
        return this.resources.getStringArray(R.array.exam_marks).toList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = getMarksList()

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = StudentRecyclerAdapter(fillList())

    }
}