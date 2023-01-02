package com.company.teachers.ui.Dialogs

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.company.teachers.R

class LoadingDialog(private var activity: Activity) {
    private lateinit var dialog: AlertDialog

    fun startLoading() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater

        builder.setView(inflater.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    fun hideLoading() {
        dialog.dismiss()
    }
}