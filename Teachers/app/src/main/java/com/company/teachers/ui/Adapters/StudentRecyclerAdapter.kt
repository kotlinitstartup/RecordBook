package com.company.teachers.ui.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.company.teachers.R
import com.company.teachers.dto.Student
import com.company.teachers.ui.Holders.StudentHolder

class StudentRecyclerAdapter(private val students: List<Student>):
    RecyclerView.Adapter<StudentHolder>() {

    lateinit var adapter : ArrayAdapter<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.student_list_item, parent, false)

        val items = parent.resources.getStringArray(R.array.exam_marks).toList()
        adapter = ArrayAdapter(parent.context, R.layout.list_item, items)

        return StudentHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val student = students[position]
        holder.fullname.text = "${student.surname} ${student.name}"
        holder.recordbookId.text = "${student.recordBookId}"
        (holder.mark.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    override fun getItemCount(): Int {
        return students.size
    }
}