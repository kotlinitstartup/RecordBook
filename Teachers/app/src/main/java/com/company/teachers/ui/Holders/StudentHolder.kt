package com.company.teachers.ui.Holders

import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.teachers.R
import com.google.android.material.textfield.TextInputLayout

class StudentHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val fullname: TextView = itemView.findViewById(R.id.student_fullname)
    val recordbookId: TextView = itemView.findViewById(R.id.student_recordbook)
    val mark: AutoCompleteTextView = itemView.findViewById(R.id.student_mark)
}