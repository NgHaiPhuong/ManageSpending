package com.example.managespending.presentation.home

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managespending.databinding.FragmentHomeBinding
import com.example.managespending.db.dao.TransactionDao
import com.example.managespending.db.database.MyDatabase
import com.example.managespending.presentation.activity.InsertActivity
import com.example.managespending.presentation.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var controller: HomeController
    private lateinit var layoutManager : LinearLayoutManager
    private lateinit var dao:TransactionDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = MyDatabase.getInstance(requireContext().applicationContext).transactionDao()
        setupRecyclerView()
        handleEvent()
    }

    private fun setupRecyclerView() {
        controller = HomeController()
        layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        controller.spanCount = 1
        binding.inforDetails.layoutManager = layoutManager

        binding.inforDetails.setControllerAndBuildModels(controller)

            controller = HomeController()
            controller.listTransaction = dao.getAllTransaction()
            controller.listTransactionByDate = dao.getTransactionByDate()


            binding.inforDetails.setControllerAndBuildModels(controller)


    }

    private fun handleEvent() {
         binding.imgsetting.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        // xử lý nút back
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (requireActivity() as MainActivity).handleBackpress()
            }

        })

        binding.imgadd.setOnClickListener {
            startActivity(Intent(requireContext(), InsertActivity::class.java).apply {
                this.action = com.example.managespending.constant.ACTION_INSERT
            })
        }
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