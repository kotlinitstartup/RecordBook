package com.company.teachers.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.company.teachers.dto.StudentsMarks
import java.lang.Exception
import java.util.ArrayList


class SQLiteHandler(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "STUDENTS_MARKS_DB"
        private const val DB_VERSION = 1
        private const val STUDENTS_MARKS = "STUDENTS_MARKS"

        private const val ID_STUDENTS_MARKS = "ID"
        private const val MARK = "MARK"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query_notes = ("CREATE TABLE " + STUDENTS_MARKS + " ("
                + ID_STUDENTS_MARKS + " INTEGER PRIMARY KEY, "
                + MARK + " TEXT)")
        db.execSQL(query_notes)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_MARKS)
        onCreate(db)
    }

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.execSQL("PRAGMA foreign_keys = ON")
    }

    fun addStudentsMarks(studentsMarks: StudentsMarks) {
        try {
            val db: SQLiteDatabase = this.getWritableDatabase()
            val values = ContentValues()
            values.put(ID_STUDENTS_MARKS, studentsMarks.id)
            values.put(MARK, studentsMarks.mark)
            db.insert(STUDENTS_MARKS, null, values)
            db.close()
        } catch (ex: Exception) {
            Log.d("addStudentsMarks(): ", ex.message!!)
        }
    }

    val studentsMarks: List<Any>
        get() {
            val studentsMarksList: ArrayList<StudentsMarks> = ArrayList<StudentsMarks>()
            try {
                val db: SQLiteDatabase = this.getReadableDatabase()
                val cursor: Cursor = db.rawQuery("SELECT * FROM " + STUDENTS_MARKS, null)
                if (cursor.moveToFirst()) {
                    do {
                        studentsMarksList.add(
                            StudentsMarks(
                                cursor.getInt(0),
                                cursor.getString(1)
                            )
                        )
                    } while (cursor.moveToNext())
                }
                cursor.close()
                return studentsMarksList
            } catch (ex: Exception) {
                Log.d("getNotes(): ", ex.message!!)
            }
            return studentsMarksList
        }

    fun getStudentsMark(id: Int): StudentsMarks? {
        try {
            val db: SQLiteDatabase = this.getReadableDatabase()
            val cursor: Cursor = db.rawQuery(
                "SELECT * FROM " + STUDENTS_MARKS + " WHERE " + ID_STUDENTS_MARKS + "=?",
                arrayOf(id.toString())
            )
            cursor.moveToFirst()
            val note = StudentsMarks(
                cursor.getInt(0),
                cursor.getString(1)
            )
            cursor.close()
            return note
        } catch (ex: Exception) {
            Log.d("getStudentMark(): ", ex.message!!)
        }
        return null
    }

    fun deleteStudentsMark(id: Int) {
        try {
            val db: SQLiteDatabase = this.getWritableDatabase()
            db.delete(STUDENTS_MARKS, ID_STUDENTS_MARKS+"=?", arrayOf(id.toString()))
            db.close()
        } catch (ex: Exception) {
            Log.d("deleteStudentsMark(): ", ex.message!!)
        }
    }


    fun updateStudentsMark(id: Int, studentsMark: StudentsMarks) {
        try {
            val db: SQLiteDatabase = this.getWritableDatabase()
            val values = ContentValues()
            values.put(ID_STUDENTS_MARKS, studentsMark.id)
            values.put(MARK, studentsMark.mark)
            db.update(STUDENTS_MARKS, values, ID_STUDENTS_MARKS+"=?", arrayOf(id.toString()))
            db.close()
        } catch (ex: Exception) {
            Log.d("updateStudentsMark(): ", ex.message!!)
        }
    }
}