package com.example.managespending.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managespending.databinding.FragmentHomeBinding
import com.example.managespending.db.dao.MyDao
import com.example.managespending.db.database.MyDatabase
import com.example.managespending.db.viewmodel.MyViewModel
import com.example.managespending.db.viewmodel.MyViewModelFactory
import com.example.managespending.model.Transaction
import com.example.managespending.presentation.insert.InsertActivity
import com.example.managespending.presentation.activity.MainActivity


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var controller: HomeController
    private lateinit var layoutManager : GridLayoutManager
    private lateinit var myViewModel: MyViewModel
    private lateinit var dao : MyDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDatabase()
        setupRecyclerView()
        handleEvent()
    }

    private fun setupDatabase() {
        dao = MyDatabase.getInstance(requireContext().applicationContext).myDao()
        val factory = MyViewModelFactory(dao)
        myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
      //  myViewModel.addAllCategory(GetListCategory.addData())
    }

    private fun setupRecyclerView() {
        controller = HomeController()
        layoutManager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        controller.spanCount = 1
        binding.inforDetails.layoutManager = layoutManager
        binding.inforDetails.setControllerAndBuildModels(controller)


        myViewModel.allTransactionList.observe(viewLifecycleOwner) { transactions ->
            controller.listTransaction = transactions.toMutableList()
            controller.requestModelBuild()
        }
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