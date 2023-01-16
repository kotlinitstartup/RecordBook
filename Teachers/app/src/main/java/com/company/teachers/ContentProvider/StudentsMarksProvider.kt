package com.company.teachers.ContentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.ContentUris
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.company.teachers.SQLite.SQLiteHandler
import java.lang.IllegalArgumentException

class StudentsMarksProvider : ContentProvider() {

    companion object {
        const val CONTENT_AUTHORITY = "com.company.teachers.provider"
        const val STUDENTS_MARKS_LIST_PATH = "students_marks_list"
        val CONTENT_AUTHORITY_URI =
            Uri.parse("content://" + CONTENT_AUTHORITY + "/" + STUDENTS_MARKS_LIST_PATH)
        const val URI_STUDENTS_MARKS = 1
        const val URI_STUDENTS_MARK_ID = 2
        private var uriMatcher: UriMatcher? = null

        init {
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            uriMatcher!!.addURI(CONTENT_AUTHORITY, STUDENTS_MARKS_LIST_PATH, URI_STUDENTS_MARKS)
            uriMatcher!!.addURI(CONTENT_AUTHORITY, STUDENTS_MARKS_LIST_PATH + "/#", URI_STUDENTS_MARK_ID
            )
        }
    }

    lateinit var db: SQLiteHandler
    override fun onCreate(): Boolean {
        db = SQLiteHandler(context)
        return true
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor {
        var selection = selection
        var sortOrder = sortOrder
        when (uriMatcher!!.match(uri)) {
            URI_STUDENTS_MARKS -> if (sortOrder == null) sortOrder = "title ASC"
            URI_STUDENTS_MARK_ID -> {
                val gID = uri.lastPathSegment
                selection = if (selection == null) String.format(
                    "ID_STUDENTS_MARKS = %s",
                    gID
                ) else selection + String.format(" and ID_STUDENTS_MARKS = %s", gID)
            }
            else -> throw IllegalArgumentException("Wrong URI $uri")
        }
        var cursor: Cursor = db.getWritableDatabase().query(
            "STUDENTS_MARKS", projection, selection, selectionArgs,
            null, null, sortOrder
        )
        cursor.setNotificationUri(context!!.contentResolver, CONTENT_AUTHORITY_URI)
        Log.d("QUERY_Provider(): ", "SUCCESS")
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        val result: Uri
        val rowID: Long
        if (uriMatcher!!.match(uri) == URI_STUDENTS_MARKS) {
            rowID = db.getWritableDatabase().insert("STUDENTS_MARKS", null, values)
            result = ContentUris.withAppendedId(CONTENT_AUTHORITY_URI, rowID)
        } else {
            throw IllegalArgumentException("Wrong URI $uri")
        }
        context!!.contentResolver.notifyChange(result, null)
        Log.d("INSERT_Provider(): ", "SUCCESS")
        return result
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var selection = selection
        selection = when (uriMatcher!!.match(uri)) {
            URI_STUDENTS_MARKS -> if (selection == null) "ID_STUDENTS_MARKS = ID_STUDENTS_MARKS" else "$selection and ID_STUDENTS_MARKS = ID_STUDENTS_MARKS"
            URI_STUDENTS_MARK_ID -> {
                val gID = uri.lastPathSegment
                if (selection == null) String.format(
                    "ID_STUDENTS_MARKS = %s",
                    gID
                ) else selection + String.format(" and ID_STUDENTS_MARKS = %s", gID)
            }
            else -> throw IllegalArgumentException("Wrong URI $uri")
        }
        val rowCount: Int = db.getWritableDatabase().delete("STUDENTS_MARKS", selection, selectionArgs)
        Log.d("RowCount: ", rowCount.toString())
        context!!.contentResolver.notifyChange(uri, null)
        Log.d("DELETE_Provider(): ", "SUCCESS")
        return rowCount
    }

    override fun update(
        uri: Uri, values: ContentValues?,
        selection: String?, selectionArgs: Array<String>?
    ): Int {
        var selection = selection
        selection = when (uriMatcher!!.match(uri)) {
            URI_STUDENTS_MARK_ID -> {
                val gID = uri.lastPathSegment
                if (selection == null) String.format(
                    "ID_STUDENTS_MARKS = %s",
                    gID
                ) else selection + String.format(" and ID_STUDENTS_MARKS = %s", gID)
            }
            else -> throw IllegalArgumentException("Wrong URI $uri")
        }
        val rowCount: Int =
            db.getWritableDatabase().update("STUDENTS_MARKS", values, selection, selectionArgs)
        Log.d("RowCount: ", rowCount.toString())
        context!!.contentResolver.notifyChange(uri, null)
        Log.d("UPDATE_Provider(): ", "SUCCESS")
        return rowCount
    }


}