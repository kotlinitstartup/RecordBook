package com.company.teachers.ViewModels

import android.app.Application
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.company.teachers.R
import com.company.teachers.ui.Fragments.ForgotPasswordFragment

class MainViewModel(application: Application, val text: String) : AndroidViewModel(application) {
    val liveData = MutableLiveData<String>()

    init {

    }

    fun StartTimer() {
        object : CountDownTimer(60000, 1000) {
            override fun onFinish() {
                liveData.value = " "
            }
            override fun onTick(millisUntilFinished: Long) {
                liveData.value = (millisUntilFinished / 1000).toString()
            }
        }.start()

    }
}
