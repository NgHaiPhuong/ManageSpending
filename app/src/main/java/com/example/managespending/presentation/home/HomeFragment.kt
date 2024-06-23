package com.example.managespending.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managespending.databinding.FragmentHomeBinding
import com.example.managespending.db.database.MyDatabase
import com.example.managespending.db.viewmodel.MyViewModel
import com.example.managespending.db.viewmodel.MyViewModelFactory
import com.example.managespending.presentation.insert.InsertActivity
import com.example.managespending.presentation.activity.MainActivity


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var controller: HomeController
    private lateinit var layoutManager : GridLayoutManager
    private lateinit var myViewModel: MyViewModel

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
        val dao = MyDatabase.getInstance(requireContext().applicationContext).myDao()
        val factory = MyViewModelFactory(dao)
        myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
      //  myViewModel.addAllCategory(GetList.addData())
      //  myViewModel.addAllTransaction(GetList.addDataTran())
    }

    private fun setupRecyclerView() {
        val bundle = arguments ?: Bundle().apply {
            putString("money1", "0.0")
            putString("limit1", "0.0")
        }
        val money = bundle.getString("money") ?: "0.0"
        val limit = bundle.getString("limit") ?: "0.0"

        Log.d("cost", "fragment: $money")
        Log.d("cost", limit)

        controller = HomeController()
        layoutManager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        controller.spanCount = 1
        binding.inforDetails.layoutManager = layoutManager
        binding.inforDetails.setControllerAndBuildModels(controller)

        controller.income1 = money.toFloatOrNull() ?: 0f
        controller.limit = limit.toFloatOrNull() ?: 0f
        controller.requestModelBuild()

        myViewModel.allTransactionList.observe(viewLifecycleOwner) { transactions ->
            controller.listTransaction = transactions.toMutableList()
            controller.requestModelBuild()
        }
        val itemDecoration : RecyclerView.ItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.inforDetails.addItemDecoration(itemDecoration)
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
        fun newInstance() : HomeFragment {
            val args =Bundle() // đóng gói dữ liệu
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}