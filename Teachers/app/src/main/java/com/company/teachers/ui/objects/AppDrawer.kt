package com.company.teachers.ui.objects

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.mikepenz.materialdrawer.Drawer

class AppDrawer(val mainActivity: AppCompatActivity, val toolbar: Toolbar) {


    fun create(){
    }

    fun enableDrawer(){
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener{
            mainActivity.supportFragmentManager.popBackStack()
        }
    }

    fun disableDrawer(){
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false);
    }
}