package com.company.teachers.utils

import androidx.fragment.app.Fragment
import com.company.teachers.R

fun Fragment.replaceFragment (fragment: Fragment){
    this.activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
        ?.replace(R.id.main_fragment_container, fragment)?.commit()
}