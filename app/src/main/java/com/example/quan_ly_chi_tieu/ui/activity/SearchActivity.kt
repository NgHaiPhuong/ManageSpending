package com.example.quan_ly_chi_tieu.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quan_ly_chi_tieu.databinding.ActivitySearchBinding
import com.example.quan_ly_chi_tieu.presentation.main.MainActivity

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgback.setOnClickListener {
            startActivity(Intent(this@SearchActivity, MainActivity::class.java))
            finish()
        }
    }
}