package com.company.teachers.viewModels

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

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
