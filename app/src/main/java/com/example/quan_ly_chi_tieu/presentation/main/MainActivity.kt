package com.example.quan_ly_chi_tieu.presentation.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.quan_ly_chi_tieu.R
import com.example.quan_ly_chi_tieu.base.BaseActivity
import com.example.quan_ly_chi_tieu.databinding.ActivityMainBinding
import com.example.quan_ly_chi_tieu.model.Category
import com.example.quan_ly_chi_tieu.presentation.category.CategoryFragment
import com.example.quan_ly_chi_tieu.presentation.chart.ChartFragment
import com.example.quan_ly_chi_tieu.presentation.home.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meow.bottomnavigation.MeowBottomNavigation

class MainActivity : BaseActivity() {
    private lateinit var bottomNavigation : MeowBottomNavigation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        setupView()
        handleEvent()
    }

    private fun handleEvent() {
        displayBottomNav()
    }

    private fun displayBottomNav(){
        bottomNavigation = findViewById(R.id.bottomNavigation)

        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.home_notactive))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.chart_noactive))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.category_noactive))
        bottomNavigation.show(0, true)

        bottomNavigation.setOnShowListener { model ->
            when (model.id) {
                1 -> {
                    // Show Home Fragment
                    replaceFragment(HomeFragment())
                }
                2 -> {
                    // Show Search Fragment
                    replaceFragment(ChartFragment())
                }
                3 -> {
                    // Show Profile Fragment
                    replaceFragment(CategoryFragment())
                }
            }
            Unit
        }
    }

    private fun updateBottomNav(selectedItemId: Int) {
        val homeIcon = if (selectedItemId == 1) R.drawable.home_active else R.drawable.home_notactive
        val chartIcon = if (selectedItemId == 2) R.drawable.chart_active else R.drawable.chart_noactive
        val categoryIcon = if (selectedItemId == 3) R.drawable.phanloai else R.drawable.category_noactive

        bottomNavigation.clearAllCounts()// Clear all models

        bottomNavigation.add(MeowBottomNavigation.Model(1, homeIcon))
        bottomNavigation.add(MeowBottomNavigation.Model(2, chartIcon))
        bottomNavigation.add(MeowBottomNavigation.Model(3, categoryIcon))

        bottomNavigation.show(selectedItemId) // Ensure the selected item is highlighted
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupView() {
        bottomNavigation.show(1)
    }

    private fun initData() {

    }
}