package com.example.managespending.presentation.category

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.managespending.databinding.FragmentCategoryBinding
import com.example.managespending.presentation.activity.MainActivity
import com.example.managespending.presentation.viewpager.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var isFiltering = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        handleEvent()
    }

    private fun handleEvent() {
        binding.imgback.setOnClickListener {
            (activity as MainActivity).toFragment()
        }
        binding.etsearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                isFiltering = binding.etsearch.text.toString() != ""
                initData(binding.etsearch.text.toString())
            }
        })
    }

    private fun initData(keyword:String){

    }

    private fun setupView() {
        viewPagerAdapter = ViewPagerAdapter(this)
        binding.viewpager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = "Spend"
                1 -> tab.text = "Income"
            }
        }.attach()
    }

    companion object {
        fun newInstance() : CategoryFragment {
            val args = Bundle()
            val fragment = CategoryFragment()
            fragment.arguments = args
            return fragment
        }
    }
}