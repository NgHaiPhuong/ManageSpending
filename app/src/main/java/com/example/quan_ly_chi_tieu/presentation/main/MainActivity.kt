package com.example.quan_ly_chi_tieu.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.quan_ly_chi_tieu.R
import com.example.quan_ly_chi_tieu.base.BaseActivity
import com.example.quan_ly_chi_tieu.constant.DARK_THEME
import com.example.quan_ly_chi_tieu.constant.FRAGMENT_HOME
import com.example.quan_ly_chi_tieu.databinding.ActivityMainBinding
import com.example.quan_ly_chi_tieu.preference.MyPreferences
import com.example.quan_ly_chi_tieu.presentation.home.HomeFragment
import com.example.quan_ly_chi_tieu.ui.ChartActivity
import com.example.quan_ly_chi_tieu.ui.InsertActivity
import com.example.quan_ly_chi_tieu.ui.ManageActivity
import com.example.quan_ly_chi_tieu.ui.SearchActivity

class MainActivity : BaseActivity() {
    private lateinit var binding : ActivityMainBinding
    private var curFragment = FRAGMENT_HOME
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        setupView()
        handleEvent()
    }

    private fun handleEvent() {

    }

    private fun setupView() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .addToBackStack(null)
            .commit()

        binding.bottomNav.selectedItemId = R.id.btnhome
    }

    private fun initData() {

    }
}