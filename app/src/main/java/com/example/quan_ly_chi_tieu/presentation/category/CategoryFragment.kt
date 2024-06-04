package com.example.quan_ly_chi_tieu.presentation.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quan_ly_chi_tieu.databinding.FragmentCategoryBinding
import com.example.quan_ly_chi_tieu.db.database.MyDatabase
import com.example.quan_ly_chi_tieu.model.Category
class CategoryFragment : Fragment() {
    private var _binding : FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private var isFragmentRunning = false
    private lateinit var myDatabase: MyDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        setupView()
        handleEvent()
    }

    private fun handleEvent() {

    }

    private fun setupView() {

    }

    private fun initData() {
        addCategory()
    }

    private fun addCategory(){
        myDatabase = MyDatabase.getInstance(requireContext().applicationContext)
        myDatabase.addCategory(
            Category(1,"Ô tô", requireContext().assets.open("spend/car.png").toString(),"#000000","Spend")
        );
    }

    companion object {
        fun newInstance() : CategoryFragment{
            val args = Bundle()
            val fragment = CategoryFragment()
            fragment.arguments = args
            return fragment
        }
    }
}