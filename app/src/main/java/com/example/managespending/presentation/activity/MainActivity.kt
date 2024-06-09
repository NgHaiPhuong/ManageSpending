package com.example.managespending.presentation.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.managespending.R
import com.example.managespending.base.BaseActivity
import com.example.managespending.db.dao.CategoryDao
import com.example.managespending.db.dao.TransactionDao
import com.example.managespending.db.database.MyDatabase
import com.example.managespending.model.Category
import com.example.managespending.model.Transaction
import com.example.managespending.presentation.category.CategoryFragment
import com.example.managespending.presentation.chart.ChartFragment
import com.example.managespending.presentation.home.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : BaseActivity() {
    private lateinit var bottomNavigation : MeowBottomNavigation
    private var selectedItemId = 1
    private lateinit var myDatabase: MyDatabase
    private lateinit var dao : TransactionDao
    private lateinit var dao1 : CategoryDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            myDatabase = MyDatabase.getInstance(application)

        }

        initData()
        setupView()
        handleEvent()
    }

    fun handleBackpress() {
        replaceFragment(HomeFragment())
        selectedItemId = 1
    }

    private fun handleEvent() {

    }

    private fun displayBottomNav(){
        bottomNavigation = findViewById(R.id.bottomNavigation)

        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.category_noactive))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.home_notactive))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.chart_noactive))

        bottomNavigation.setOnShowListener { model ->
            when (model.id) {
                1 -> {
                   // updateBottomNav(1)
                    replaceFragment(CategoryFragment())
                }
                2 -> {
                   // updateBottomNav(2)
                    replaceFragment(HomeFragment())
                }
                3 -> {
                  //  updateBottomNav(3)
                    replaceFragment(ChartFragment())
                }
            }
            Unit
        }
        bottomNavigation.show(2, true)
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupView() {
        replaceFragment(HomeFragment.newInstance())
        displayBottomNav()
    }

    private fun initData() {

    }
}