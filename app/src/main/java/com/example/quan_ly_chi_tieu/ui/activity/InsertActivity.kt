package com.example.quan_ly_chi_tieu.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.quan_ly_chi_tieu.databinding.ActivityInsertBinding
import com.example.quan_ly_chi_tieu.presentation.main.MainActivity
import java.util.Calendar


class InsertActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInsertBinding
    private var tinhtoan = ""
    private var pheptinh = ""
    private var so1 : Float = 0F
    private var so2 : Float = 0F
    private var kq : Float = 0F
    private var checkbang = false
    private var checkDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEvent()
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun handleEvent() {

        binding.tvnumber.setOnClickListener {
            binding.calculation.visibility = View.VISIBLE
            binding.epxlistSearch.visibility = View.GONE
            binding.layout.visibility = View.GONE
            calculation()
            binding.viewDimBackground.visibility = View.VISIBLE
        }

        if(binding.btntru.isActivated || binding.btncong.isActivated||
            binding.btnnhan.isActivated || binding.btnchia.isActivated){
            binding.tvsum.visibility = View.VISIBLE
        }

        binding.viewDimBackground.setOnClickListener {
            binding.viewDimBackground.visibility = View.GONE
            binding.calculation.visibility = View.GONE
            binding.epxlistSearch.visibility = View.GONE
            binding.layout.visibility = View.GONE
        }

        binding.imgsave.setOnClickListener{

        }

        /*binding.root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (binding.calculation.visibility == View.VISIBLE &&
                    !isPointInsideView(event.x, event.y, binding.calculation)
                ) {
                    binding.calculation.visibility = View.GONE
                }
            }
            false
        }*/
    }
    private fun isPointInsideView(x: Float, y: Float, view: View): Boolean {
        val location = intArrayOf(0, 0)
        view.getLocationOnScreen(location)
        val viewX = location[0]
        val viewY = location[1]
        val viewWidth = view.width
        val viewHeight = view.height
        return x >= viewX && x <= viewX + viewWidth && y >= viewY && y <= viewY + viewHeight
    }
    @SuppressLint("SetTextI18n")
    private fun calculation(){
        binding.btnclear.setOnClickListener {
            binding.tvnumber.text = ""
            binding.tvsum.text = ""
            binding.tvnumber.hint = "0"
            binding.tvsum.hint = "0"
            tinhtoan = ""
            pheptinh = ""
            checkDot = false
            checkbang = false
        }

        binding.btn1.setOnClickListener {
            if(!checkbang){
                tinhtoan = binding.tvnumber.text.toString()
                binding.tvnumber.setText(tinhtoan + "1")
            }
        }

        binding.btn2.setOnClickListener {
            if(!checkbang){
                tinhtoan = binding.tvnumber.text.toString()
                binding.tvnumber.setText(tinhtoan + "2")
            }
        }

        binding.btn3.setOnClickListener {
            if(!checkbang){
                tinhtoan = binding.tvnumber.text.toString()
                binding.tvnumber.setText(tinhtoan + "3")
            }
        }

        binding.btn4.setOnClickListener {
            if(!checkbang){
                tinhtoan = binding.tvnumber.text.toString()
                binding.tvnumber.setText(tinhtoan + "4")
            }
        }

        binding.btn5.setOnClickListener {
            if(!checkbang){
                tinhtoan = binding.tvnumber.text.toString()
                binding.tvnumber.setText(tinhtoan + "5")
            }
        }

        binding.btn6.setOnClickListener {
            if(!checkbang){
                tinhtoan = binding.tvnumber.text.toString()
                binding.tvnumber.setText(tinhtoan + "6")
            }
        }

        binding.btn7.setOnClickListener {
            if(!checkbang){
                tinhtoan = binding.tvnumber.text.toString()
                binding.tvnumber.setText(tinhtoan + "7")
            }
        }

        binding.btn8.setOnClickListener {
            if(!checkbang){
                tinhtoan = binding.tvnumber.text.toString()
                binding.tvnumber.setText(tinhtoan + "8")
            }
        }

        binding.btn9.setOnClickListener {
            if(!checkbang){
                tinhtoan = binding.tvnumber.text.toString()
                binding.tvnumber.setText(tinhtoan + "9")
            }
        }

        binding.btn0.setOnClickListener {
            if(!checkbang){
                tinhtoan = binding.tvnumber.text.toString()
                binding.tvnumber.setText(tinhtoan + "0")
            }
        }

        binding.btnpphay.setOnClickListener {
            if(!checkbang){
                if(!checkDot){
                    tinhtoan = binding.tvnumber.text.toString()
                    binding.tvnumber.setText(tinhtoan + ".")
                    checkDot = true
                }
            }
        }

        binding.btncong.setOnClickListener {
            if(binding.tvnumber.text.equals("")){
                so1 = 0F
                binding.tvhour.setText("0 + ")
            }else{
                so1 = binding.tvnumber.text.toString().toFloat()
                binding.tvsum.setText(binding.tvnumber.text.toString() + "+")
                binding.tvnumber.setText("")
                binding.tvnumber.hint = ""
                tinhtoan = ""
                pheptinh = "+"
                checkDot = false
                checkbang = false
            }
        }

        binding.btntru.setOnClickListener {
            if(binding.tvnumber.text.equals("")){
                so1 = 0F
                binding.tvhour.setText("0 - ")
            }else{
                so1 = binding.tvnumber.text.toString().toFloat()
                binding.tvsum.setText(binding.tvnumber.text.toString() + "-")
                binding.tvnumber.setText("")
                binding.tvnumber.hint = ""
                tinhtoan = ""
                pheptinh = "-"
                checkDot = false
                checkbang = false
            }
        }

        binding.btnnhan.setOnClickListener {
            if(binding.tvnumber.text.equals("")){
                so1 = 0F
                binding.tvhour.setText("0 x ")
            }else{
                so1 = binding.tvnumber.text.toString().toFloat()
                binding.tvsum.setText(binding.tvnumber.text.toString() + "x")
                binding.tvnumber.setText("")
                binding.tvnumber.hint = ""
                tinhtoan = ""
                pheptinh = "*"
                checkDot = false
                checkbang = false
            }
        }

        binding.btnchia.setOnClickListener {
            if(binding.tvnumber.text.equals("")){
                so1 = 0F
                binding.tvhour.setText("0 / ")
            }else{
                so1 = binding.tvnumber.text.toString().toFloat()
                binding.tvsum.setText(binding.tvnumber.text.toString() + "/")
                binding.tvnumber.setText("")
                binding.tvnumber.hint = ""
                tinhtoan = ""
                pheptinh = "/"
                checkDot = false
                checkbang = false
            }
        }

        binding.btnbang.setOnClickListener {
            if(!checkbang){
                checkDot = false
                tinhtoan = binding.tvnumber.text.toString()
                so2 = binding.tvnumber.text.toString().toFloat()
                binding.tvsum.setText(binding.tvsum.text.toString() + so2.toString())
                if(pheptinh == "+")
                    kq = so1 + so2
                else if(pheptinh == "-")
                    kq = so1 - so2
                else if(pheptinh == "*")
                    kq = so1*so2
                else
                    kq = so1/so2
                checkbang = true

                binding.tvnumber.setText(kq.toString() + "")
            }
        }

        binding.btndelete.setOnClickListener {
            if(!checkbang){
                var text = binding.tvnumber.text.toString()
                if(!text.isEmpty()){
                    text = text.substring(0,text.length - 1)
                    binding.tvnumber.setText(text)
                }
            }
        }
    }

    fun btnShowCanlender(view: View) {
        binding.calculation.visibility = View.GONE
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

       /* DatePickerDialog(
            this@InsertActivity,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                binding.tvdate.text = String.format("%d-%d-%d", dayOfMonth, month, year)
            }, year, month, day
        ).show()*/

        val datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            binding.tvdate.text = String.format("%d-%d-%d", dayOfMonth, month, year)
        }, year, month,day)
        datePickerDialog.show()
    }

    fun btnShowOClock(view: View) {
        binding.calculation.visibility = View.GONE
        val calender = Calendar.getInstance()
        val hour = calender.get(Calendar.HOUR)
        val minute = calender.get(Calendar.MINUTE)

        binding.tvhour.text = String.format("%d:%d", hour, minute)

        TimePickerDialog(
            this@InsertActivity,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                binding.tvhour.text= String.format(" %d:%d", hourOfDay, minute).toString()
            }, hour, minute, true
        ).show()

        /*val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            binding.tvhour.text= String.format(" %d:%d", hourOfDay, minute).toString()
        }, hour, minute, true)
        timePickerDialog.show()*/
    }

    fun showList(view: View) {
        binding.layout.visibility = View.VISIBLE
        binding.epxlistSearch.visibility = View.VISIBLE
        binding.calculation.visibility = View.GONE
        binding.viewDimBackground.visibility = View.VISIBLE
    }

    fun showManager(view: View) {
        startActivity(Intent(this@InsertActivity, ManageActivity::class.java))
        finish()
    }

    fun showmain(view:View){
        startActivity(Intent(this@InsertActivity, MainActivity::class.java))
        finish()
    }


}



