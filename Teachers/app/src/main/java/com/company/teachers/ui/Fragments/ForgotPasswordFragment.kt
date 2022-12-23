package com.company.teachers.ui.Fragments

import android.app.Application
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.company.teachers.R
import com.company.teachers.ViewModels.MainFactory
import com.company.teachers.ViewModels.MainViewModel
import org.w3c.dom.Text
import java.util.concurrent.CountDownLatch

class ForgotPasswordFragment : BaseFragment(R.layout.fragment_forgot_password) {

    lateinit var mViewModel: MainViewModel

    override fun onResume() {
        super.onResume()
        initFunc()

        var leftTime = view?.findViewById<TextView>(R.id.left_time_textView)
        var sendPasswordToEmailButton =  view?.findViewById<TextView>(R.id.sendPasswordToEmailButton)

        mViewModel = ViewModelProvider(this, MainFactory(application = Application(), "hello"))
            .get(MainViewModel::class.java)
        mViewModel.liveData.observe(this, Observer {
            leftTime?.text = it

            if (it == " "){
                sendPasswordToEmailButton?.visibility = View.VISIBLE
                leftTime?.visibility = View.INVISIBLE
            }
        })

        sendPasswordToEmailButton?.setOnClickListener{
            sendPasswordToEmailButton.visibility = View.INVISIBLE
            leftTime?.visibility = View.VISIBLE
            mViewModel.StartTimer()
        }



    }

    private fun initFunc() {

    }
}