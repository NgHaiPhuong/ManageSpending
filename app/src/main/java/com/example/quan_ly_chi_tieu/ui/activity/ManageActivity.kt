package com.example.quan_ly_chi_tieu.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quan_ly_chi_tieu.databinding.ActivityManageBinding
import com.example.quan_ly_chi_tieu.presentation.main.MainActivity

class ManageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityManageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgback.setOnClickListener {
            startActivity(Intent(this@ManageActivity, MainActivity::class.java))
            finish()
        }
    }
}