package com.example.managespending.presentation.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.managespending.base.BaseActivity
import com.example.managespending.databinding.FragmentChartBinding
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

class ChartActivity : BaseActivity(), OnChartValueSelectedListener {
    private lateinit var binding : FragmentChartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleEvent()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun handleEvent(){
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

        binding.recyclermonth.layoutManager = LinearLayoutManager(this)
        binding.recyclermonth.adapter = com.example.managespending.date.MonthAdapter()

        binding.recycleryear.layoutManager = LinearLayoutManager(this)
        binding.recycleryear.adapter = com.example.managespending.date.YearAdapter()
        /*binding.root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if ((binding.layoutPL.visibility == View.VISIBLE ||
                     binding.listmonth.visibility == View.VISIBLE) &&
                    !isPointInsideView(event.x, event.y, binding.layoutPL)
                ) {
                    binding.layoutPL.visibility = View.GONE
                }
            }
            false
        }*/
    }
    /*private fun isPointInsideView(x: Float, y: Float, view: View): Boolean {
        val location = intArrayOf(0, 0)
        view.getLocationOnScreen(location)
        val viewX = location[0]
        val viewY = location[1]
        val viewWidth = view.width
        val viewHeight = view.height
        return x >= viewX && x <= viewX + viewWidth && y >= viewY && y <= viewY + viewHeight
    }*/
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
            description.typeface = Typeface.create(Typeface.DEFAULT, BOLD)
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

    fun showimgback(view: View) {
        startActivity(Intent(this@ChartActivity, MainActivity::class.java))
        finish()
    }
}

