package com.example.managespending.presentation.chart

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.managespending.databinding.FragmentChartBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class ChartFragment : Fragment(){
    private lateinit var binding : FragmentChartBinding

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
        initData()
        handleEvent()
    }
    @SuppressLint("ClickableViewAccessibility")
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
    }

    private fun initData() {

    }

    private fun setupView(){

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