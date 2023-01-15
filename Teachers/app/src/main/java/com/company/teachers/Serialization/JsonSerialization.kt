package com.company.teachers.Serialization

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.company.teachers.dto.Student
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStreamReader
import java.io.Serializable


class JsonSerialization(private val fileName: String) : Serializable {
    fun exportToJSON(context: Context, dataList: List<Student?>?): Boolean {
        val gson = Gson()
        val dataItems = DataItems()
        dataItems.students = dataList
        val jsonString = gson.toJson(dataItems)
        try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { fileOutputStream ->
                fileOutputStream.write(jsonString.toByteArray())
                return true
            }
        } catch (ex: Exception) {
            Toast.makeText(context, "Ошибка экспорта", Toast.LENGTH_SHORT).show()
            Log.d("exportToJSON(): ", ex.message!!)
        }
        return false
    }

    fun importFromJSON(context: Context): List<Student?>? {
        try {
            context.openFileInput(fileName).use { fileInputStream ->
                InputStreamReader(fileInputStream).use { streamReader ->
                    val gson = Gson()
                    val dataItems = gson.fromJson(streamReader, DataItems::class.java)
                    return dataItems.students
                }
            }
        } catch (ex: IOException) {
            Toast.makeText(context, "Ошибка импорта", Toast.LENGTH_SHORT).show()
            Log.d("importFromJSON(): ", ex.message!!)
        }
        return null
    }

    private class DataItems {
        var students: List<Student?>? = null
    }
}