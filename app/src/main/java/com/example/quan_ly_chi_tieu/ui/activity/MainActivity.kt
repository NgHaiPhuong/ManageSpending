package com.example.quan_ly_chi_tieu.ui

import android.content.Intent
import android.os.Bundle
import com.example.quan_ly_chi_tieu.base.BaseActivity
import com.example.quan_ly_chi_tieu.constant.DARK_THEME
import com.example.quan_ly_chi_tieu.databinding.ActivityMainBinding
import com.example.quan_ly_chi_tieu.preference.MyPreferences

class MainActivity : BaseActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        setupView()
        handleEvent()
    }

    private fun handleEvent() {
        binding.imgMenu.setOnClickListener {
           /* if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }*/

            MyPreferences.write(MyPreferences.PREF_CURRENT_THEME, DARK_THEME)
            recreate()
        }

        binding.imgSearch.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            finish()
        }

        binding.imglist.setOnClickListener {
            startActivity(Intent(this@MainActivity, ManageActivity::class.java))
            finish()
        }

        binding.imgplus.setOnClickListener {
            startActivity(Intent(this@MainActivity, InsertActivity::class.java))
            finish()
        }

        binding.imgChart.setOnClickListener {
            startActivity(Intent(this@MainActivity, ChartActivity::class.java))
            finish()
        }
    }

    private fun setupView() {

    }

    private fun initData() {

    }
}