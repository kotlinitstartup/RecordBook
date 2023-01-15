package com.company.teachers.ui.Adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.company.teachers.R
import com.company.teachers.Serialization.JsonSerialization
import com.company.teachers.dto.Student
import com.company.teachers.dto.StudentResponse
import com.company.teachers.dto.StudentsMarks
import com.company.teachers.ui.Holders.StudentHolder

class StudentRecyclerAdapter(private val studentsResponse: List<StudentResponse>) :
    RecyclerView.Adapter<StudentHolder>() {

    lateinit var adapter: ArrayAdapter<String>
    var updatedStudents = ArrayList<StudentsMarks>()
    var updatedStudentsList = ArrayList<Student>()
    var creditTypes = ArrayList<String>()
    var jsonSerialization = JsonSerialization("studentInfo.json")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_item, parent, false)

        itemView.setOnLongClickListener {val builder = AlertDialog.Builder(parent.context)
            builder.setTitle("JsonSerializer")
            builder.setMessage("Сериализовать всех изменненых студентов?")
            builder.setPositiveButton("OK") { dialog, which ->
                jsonSerialization.exportToJSON(parent.context, updatedStudentsList)
                Toast.makeText(parent.context, "Данные сохранены", Toast.LENGTH_SHORT)
                var students = jsonSerialization.importFromJSON(parent.context)
                Toast.makeText(parent.context, students.toString(), Toast.LENGTH_SHORT)
            }
            builder.setNegativeButton("Отмена") { dialog, which ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
            true
        }

        val items = parent.resources.getStringArray(R.array.exam_marks).toList()
        creditTypes = parent.resources.getStringArray(R.array.credit_type).toList() as ArrayList<String>

        try{
            studentsResponse[0].mark.toInt()
            adapter = ArrayAdapter(parent.context, R.layout.list_item, items)
        }
        catch (e: Exception){
            adapter = ArrayAdapter(parent.context, R.layout.list_item, creditTypes)
        }

        return StudentHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val student = studentsResponse[position].student
        holder.fullname.text = "${student.lastname} ${student.firstname}"
        holder.recordbookId.text = student.recordBook.number
        holder.mark.setAdapter(adapter)

        holder.mark.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, id ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                var markIndex = creditTypes.indexOf(selectedItem)

                if (markIndex != -1){
                    updatedStudentsList += student
                    updatedStudents += StudentsMarks(student.id, markIndex.toString())
                }else{
                    updatedStudentsList += student
                    updatedStudents += StudentsMarks(student.id,  selectedItem)
                }
            }
        holder.mark.setText(studentsResponse[position].mark, false)

    }

    override fun getItemCount(): Int {
        return studentsResponse.size
    }
}