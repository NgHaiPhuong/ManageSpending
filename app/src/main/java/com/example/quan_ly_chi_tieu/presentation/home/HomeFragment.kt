package com.example.quan_ly_chi_tieu.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.core.view.GravityCompat
import com.example.quan_ly_chi_tieu.R
import com.example.quan_ly_chi_tieu.constant.DARK_THEME
import com.example.quan_ly_chi_tieu.databinding.FragmentHomeBinding
import com.example.quan_ly_chi_tieu.preference.MyPreferences
import com.example.quan_ly_chi_tieu.ui.ChartActivity
import com.example.quan_ly_chi_tieu.ui.InsertActivity
import com.example.quan_ly_chi_tieu.ui.ManageActivity
import com.example.quan_ly_chi_tieu.ui.SearchActivity

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var isFragmentRunning = false

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
        binding.imgMenu.setOnClickListener {
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