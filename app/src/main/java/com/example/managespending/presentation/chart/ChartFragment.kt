package com.example.managespending.presentation.chart

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.window.isTraySupported
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBindings
import com.example.managespending.R
import com.example.managespending.databinding.FragmentChartBinding
import com.example.managespending.presentation.viewpager.ViewPagerAdapter_Chart
import com.github.mikephil.charting.charts.Chart
import com.google.android.material.tabs.TabLayoutMediator
import org.jetbrains.skia.Drawable

class ChartFragment : Fragment(){
    private lateinit var binding : FragmentChartBinding
    private lateinit var viewpagerAdapter : ViewPagerAdapter_Chart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentChartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        handleEvent()
    }
    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    private fun handleEvent() {
        binding.btnphanloai.setOnClickListener {
            binding.viewDimBackground.visibility = View.VISIBLE
            binding.layoutPL.visibility = View.VISIBLE
        }
        binding.btndate.setOnClickListener {
            binding.viewDimBackground.visibility = View.VISIBLE
            binding.layoutdate.visibility = View.VISIBLE
        }
        binding.viewDimBackground.setOnClickListener {
            binding.viewDimBackground.visibility = View.GONE
            binding.layoutdate.visibility = View.GONE
            binding.layoutPL.visibility = View.GONE
        }

        binding.recyclermonth.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        binding.recyclermonth.adapter = com.example.managespending.date.MonthAdapter()

        binding.recycleryear.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        binding.recycleryear.adapter = com.example.managespending.date.YearAdapter()

        binding.cbothunhap.setOnCheckedChangeListener { buttonView , isChecked -> updateFragment() }
        binding.cbochitieu.setOnCheckedChangeListener { _, isChecked -> updateFragment() }
    }
    private fun updateFragment() {
        val viewPager = binding.viewChart
        val isIncomeChecked = binding.cbothunhap.isChecked
        val isExpendChecked = binding.cbochitieu.isChecked
        val fragment = childFragmentManager.findFragmentByTag("f" + viewPager.currentItem)
        if(fragment is BarFragment){
            fragment.updateChartVisibility(isIncomeChecked, isExpendChecked)
        }
        else if(fragment is LineFragment){
            fragment.updateChartVisibility(isIncomeChecked, isExpendChecked)
        }
        binding.btnok.setOnClickListener {
            binding.layoutPL.visibility = View.GONE
            binding.viewDimBackground.visibility = View.GONE
        }
    }
    private fun setupView(){
        viewpagerAdapter = ViewPagerAdapter_Chart(this)
        binding.viewChart.adapter = viewpagerAdapter

        TabLayoutMediator(binding.tab, binding.viewChart){tab, position ->
            tab.customView = createTabView(position)
        }.attach()
    }
    @SuppressLint("InflateParams")
    private fun createTabView(position: Int): View {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab, null)
        val icon = view.findViewById<ImageView>(R.id.tab_icon)
        val textView = view.findViewById<TextView>(R.id.tab_text)

        when (position) {
            0 -> {
                icon.setImageResource(R.drawable.line_chart)
                textView.text = "Bar Chart"
            }
            1 -> {
                icon.setImageResource(R.drawable.line)
                textView.text = "Line Chart"
            }
        }
        return view
    }

    companion object {
        fun newInstance() : ChartFragment {
            val args = Bundle()
            val fragment = ChartFragment()
            fragment.arguments = args
            return fragment
        }
    }
}