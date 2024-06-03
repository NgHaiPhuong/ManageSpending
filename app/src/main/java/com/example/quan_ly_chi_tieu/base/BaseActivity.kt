package com.example.quan_ly_chi_tieu.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quan_ly_chi_tieu.util.ThemeUtil

abstract class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(ThemeUtil.getTheme(this@BaseActivity))
    }

}