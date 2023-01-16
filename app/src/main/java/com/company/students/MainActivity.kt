package com.company.students

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.company.students.databinding.ActivityMainBinding
import com.company.students.ui.fragments.FiltersFragment
import com.company.students.ui.fragments.LoginFragment
import com.company.students.ui.objects.AppDrawer

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }

    override fun onStart() {
        super.onStart()

        mToolbar = _binding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)

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
}