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
import com.example.managespending.databinding.FragmentHomeBinding
import com.example.managespending.date.MonthAdapter
import com.example.managespending.date.YearAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class ChartFragment : Fragment(), OnChartValueSelectedListener {
    private var _binding : FragmentChartBinding? = null
    private val binding get() = _binding!!
    private var isFragmentRunning = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentChartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFragmentRunning = true
        setupView()
        initData()
        handleEvent()
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun handleEvent() {
        designChart()
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

    private fun designChart(){
        binding.mChart.apply {
            centerText = "Chart"
            setCenterTextSize(16f)
            setCenterTextColor(Color.parseColor("#808080"))
            setDrawEntryLabels(false)
            legend.isEnabled = true
            setTouchEnabled(true)

            setUsePercentValues(true)

            description.isEnabled = true
            description.textSize = 18f
            description.text = "CHI TIÊU"
            description.textColor = Color.parseColor("#3399FF")
            description.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            animateY(1400, Easing.EaseInOutQuad)
        }
        addDataSet(binding.mChart)

        binding.mChart.setOnChartValueSelectedListener(this)
    }
    private fun addDataSet(pieChart: PieChart) {
        val yEntrys = arrayListOf(
            PieEntry(25f, "Ô tô"),
            PieEntry(30f, "Đồ ăn"),
            PieEntry(20f, "Giáo dục")
        )
        val pieDataSet = PieDataSet(yEntrys, " ").apply {
            sliceSpace = 2f
            colors = ArrayList(listOf(Color.GRAY, Color.BLUE, Color.RED))
            setDrawValues(false)
            pieChart
        }


        val legend = pieChart.legend
        legend.form = Legend.LegendForm.SQUARE
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend.xEntrySpace = 10f
        legend.yEntrySpace = 7f
        legend.xOffset = 20f
        legend.yOffset = -20f


        val pieData = PieData(pieDataSet)
        pieChart.data = pieData
        pieChart.invalidate()
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e is PieEntry) {
            binding.mChart.centerText = e.value.toString()
        }
    }

    override fun onNothingSelected() {

    }

    private fun initData() {

    }

    private fun setupView(){

    }

    override fun onResume() {
        super.onResume()
        isFragmentRunning = true
    }

    override fun onPause() {
        super.onPause()
        isFragmentRunning = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFragmentRunning = false
    }

    companion object {
        fun newInstance() : ChartFragment{
            val args = Bundle()
            val fragment = ChartFragment()
            fragment.arguments = args
            return fragment
        }
    }
}