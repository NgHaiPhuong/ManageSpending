package com.example.quan_ly_chi_tieu.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quan_ly_chi_tieu.databinding.ActivityDetailBinding
import com.example.quan_ly_chi_tieu.presentation.main.MainActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        handleEvent()
    }

    private fun handleEvent() {
        binding.imgback.setOnClickListener {
            startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            finish()
        }
        binding.btnedit.setOnClickListener {
            startActivity(Intent(this@DetailActivity, ManageActivity::class.java))
            finish()
        }
    }
}