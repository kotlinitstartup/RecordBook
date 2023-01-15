package com.company.teachers

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.company.teachers.databinding.ActivityMainBinding
import com.company.teachers.ui.Fragments.LoginFragment
import com.company.teachers.ui.Fragments.FiltersFragment
import com.company.teachers.ui.objects.AppDrawer

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()

        initFields()
        initFunc()
    }

    private fun initFunc() {
        setSupportActionBar(mToolbar)
        mAppDrawer.create()
        var isLogined =
            getSharedPreferences("com.company.prefs", Context.MODE_PRIVATE).getString(
                "token",
                null
            ) !== null
        if (isLogined) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, FiltersFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, LoginFragment()).commit()
        }

    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
    }

}