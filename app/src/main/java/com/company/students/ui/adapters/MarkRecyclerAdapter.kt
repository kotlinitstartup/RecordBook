package com.company.students.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.students.R
import com.company.students.dto.MarkRecord
import com.company.students.ui.holders.MarkHolder

class MarkRecyclerAdapter(private val marks: List<MarkRecord>) :
    RecyclerView.Adapter<MarkHolder>() {

    lateinit var adapter: ArrayAdapter<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.mark_list_item, parent, false)

        adapter = ArrayAdapter(parent.context, R.layout.list_item)

        return MarkHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MarkHolder, position: Int) {
        val markRecord = marks[position]
        holder.teacherName.text = "${markRecord.teacher.firstname} ${markRecord.teacher.lastname}"
        holder.mark.text = "${markRecord.mark}"
        holder.date.text = "${markRecord.createdAt}"
    }

    override fun getItemCount(): Int {
        return marks.size
    }
}