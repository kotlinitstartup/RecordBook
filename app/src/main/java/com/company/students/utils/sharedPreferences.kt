package com.company.students.utils

import android.content.Context
import androidx.fragment.app.Fragment

val PREFS_FILENAME = "com.company.prefs"

fun Fragment.saveToSharedPreferences(key: String, value: Any){
    val prefs = this.context?.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    val editor = prefs?.edit()

    editor?.putString(key, value.toString())
    editor?.apply()
}

fun Fragment.getFromSharedPreferences(key: String): String?{
    val prefs = context?.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    val data = prefs?.getString(key, null)
    return data
}

fun Fragment.removeFromSharedPreferences(key: String){
    val prefs = context?.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    prefs?.edit()?.remove(key)?.commit()
}
