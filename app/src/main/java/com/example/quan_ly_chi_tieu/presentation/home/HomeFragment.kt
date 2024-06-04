package com.example.quan_ly_chi_tieu.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.example.quan_ly_chi_tieu.R
import com.example.quan_ly_chi_tieu.databinding.FragmentHomeBinding
import com.example.quan_ly_chi_tieu.db.database.MyDatabase
import com.example.quan_ly_chi_tieu.model.Category

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var isFragmentRunning = false
    private lateinit var myDatabase: MyDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFragmentRunning = true
        setupView()
        initData()
        handleEvent()
    }

    private fun handleEvent() {
        binding.imgsetting.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun initData() {

    }

    private fun setupView() {

    }

    companion object {
        fun newInstance() : HomeFragment{
            val args =Bundle() // đóng gói dữ liệu
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}