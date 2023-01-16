package com.company.students.ui.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.students.R

class MarkHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val teacherName: TextView = itemView.findViewById(R.id.teacherName)
    val mark: TextView = itemView.findViewById(R.id.mark)
    val date: TextView = itemView.findViewById(R.id.date)
}