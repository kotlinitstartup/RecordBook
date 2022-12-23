package com.company.teachers.Utilits

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.company.teachers.R
import com.company.teachers.ui.Fragments.ForgotPasswordFragment
import retrofit2.Retrofit

fun Fragment.showToast (message: String){
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.replaceFragment (fragment: Fragment){
    this.activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
        ?.replace(R.id.main_fragment_container, fragment)?.commit()
}
