package com.company.teachersextension

import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var selectET: EditText? = null
    var updateET: EditText? = null
    var insertET: EditText? = null
    var deleteET: EditText? = null
    var infoTV: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectET = findViewById(R.id.selectET)
        updateET = findViewById(R.id.updateET)
        insertET = findViewById(R.id.insertET)
        deleteET = findViewById(R.id.deleteET)
        infoTV = findViewById(R.id.infoTV)
        if (supportActionBar != null) supportActionBar!!.hide()
    }

    fun selectBtn(view: View?) {
        try {
            val def = Uri.parse("content://com.company.teachers.provider/students_marks_list")
            val cursor: Cursor?
            cursor = if (selectET!!.text.toString() != "") {
                val uri = ContentUris.withAppendedId(
                    def,
                    selectET!!.text.toString().toInt().toLong()
                )
                contentResolver.query(uri, null, null, null, null)
            } else {
                contentResolver.query(def, null, null, null, null)
            }
            var info = "Список оценок: \n"
            if (cursor!!.moveToFirst()) {
                do {
                    info += "${cursor.getString(0)} ${cursor.getString(1)}"
                } while (cursor.moveToNext())
            }
            infoTV!!.text = info
            selectET!!.setText("")
            cursor.close()
        } catch (ex: Exception) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun updateBtn(view: View?) {
        if (updateET!!.text.toString() == "") {
            Toast.makeText(this, "Заполните поле", Toast.LENGTH_LONG).show()
            return
        }
        val values = updateET!!.text.toString().split(" ").toTypedArray()
        if (values.size != 2) {
            Toast.makeText(this, "Количество параметров должно быть 2", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val cv = ContentValues()
            cv.put("ID_STUDENTS_MARKS", values[0])
            cv.put("MARK", values[1])
            val uri = Uri.parse("content://com.company.teachers.provider/students_marks_list")
            val idUri = ContentUris.withAppendedId(
                uri,
                values[0].toInt().toLong()
            )
            contentResolver.update(idUri, cv, null)
            Toast.makeText(this, "Оценка обновлена!", Toast.LENGTH_SHORT).show()
            updateET!!.setText("")
        } catch (ex: Exception) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    fun insertBtn(view: View?) {
        if (insertET!!.text.toString() == "") {
            Toast.makeText(this, "Заполните поле", Toast.LENGTH_LONG).show()
            return
        }
        val values = insertET!!.text.toString().split(" ").toTypedArray()
        if (values.size != 2) {
            Toast.makeText(this, "Количество параметров должно быть 2", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val cv = ContentValues()
            cv.put("ID_STUDENTS_MARKS", values[0])
            cv.put("MARK", values[1])
            val uri = Uri.parse("content://com.company.teachers.provider/students_marks_list")
            contentResolver.insert(uri, cv)
            Toast.makeText(this, "Оценка добавлена!", Toast.LENGTH_SHORT).show()
            insertET!!.setText("")
        } catch (ex: Exception) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    fun deleteBtn(view: View?) {
        if (deleteET!!.text.toString() == "") {
            Toast.makeText(this, "Заполните поле", Toast.LENGTH_LONG).show()
            return
        }
        try {
            val uri = Uri.parse("content://com.company.teachers.provider/students_marks_list")
            val idUri = ContentUris.withAppendedId(
                uri,
                deleteET!!.text.toString().toInt().toLong()
            )
            contentResolver.delete(idUri, null, null)
            Toast.makeText(this, "Оценка удалена!", Toast.LENGTH_SHORT).show()
            deleteET!!.setText("")
        } catch (ex: Exception) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }
}