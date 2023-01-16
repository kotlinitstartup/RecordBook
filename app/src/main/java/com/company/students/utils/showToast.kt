package com.company.students.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast (message: String){
    requireActivity().runOnUiThread{
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }
}