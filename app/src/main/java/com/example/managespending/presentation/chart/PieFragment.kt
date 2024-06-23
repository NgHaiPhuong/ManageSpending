package com.example.managespending.presentation.chart

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.managespending.R
import com.example.managespending.databinding.FragmentPieBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
class PieFragment : Fragment(), OnChartValueSelectedListener {
    private lateinit var binding : FragmentPieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        designChart()
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

    companion object {
        fun newInstance() : PieFragment {
            val args = Bundle()
            val fragment = PieFragment()
            fragment.arguments = args
            return fragment
        }
    }
}