package com.example.managespending.presentation.insert

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.managespending.R
import com.example.managespending.base.BaseActivity
import com.example.managespending.databinding.ActivityInsertBinding
import com.example.managespending.db.database.MyDatabase
import com.example.managespending.db.viewmodel.MyViewModel
import com.example.managespending.db.viewmodel.MyViewModelFactory
import com.example.managespending.model.Transaction
import com.example.managespending.presentation.activity.MainActivity
import com.example.managespending.presentation.category.CategoryFragment
import com.example.managespending.presentation.viewpager.ViewPagerAdapter
import com.example.managespending.presentation.home.HomeFragment
import com.example.managespending.presentation.viewpager.ViewPagerAdapter2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class InsertActivity : BaseActivity() {
    private lateinit var binding : ActivityInsertBinding
    private lateinit var myViewModel: MyViewModel
    private lateinit var viewPagerAdapter: ViewPagerAdapter2
    private var tinhtoan = ""
    private var pheptinh = ""
    private var so1 : Float = 0F
    private var so2 : Float = 0F
    private var kq : Float = 0F
    private var checkbang = false
    private var checkDot = false
    var iconTrans : String = " "
    var nameTrans : String = " "
    var classifyTrans : String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_insert)

        setupData()
        setupDatabase()
        setupView()
        handleEvent()
    }
    fun setupData(){
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        binding.tvdate.text = String.format("%d/%d/%d", day, month, year)

        binding.name = "Car"

        binding.calculation.visibility = View.GONE
        val hour = calender.get(Calendar.HOUR)
        val minute = calender.get(Calendar.MINUTE)
        binding.tvhour.text = String.format("%d:%d", hour, minute)
    }
    fun displayData(icon: String, name: String, classify: String) {
        iconTrans = icon
        nameTrans = name
        classifyTrans = classify

        binding.url = icon
        binding.name = name
        binding.executePendingBindings()
        iconTrans = icon
    }
    private fun setupView() {
        viewPagerAdapter = ViewPagerAdapter2(this)
        binding.viewpager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = "Spend"
                1 -> tab.text = "Income"
            }
        }.attach()
    }

    private fun setupDatabase() {
        val dao = MyDatabase.getInstance(application).myDao()
        val factory = MyViewModelFactory(dao)
        myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun handleEvent() {
        binding.tvnumber.setOnClickListener {
            binding.calculation.visibility = View.VISIBLE
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
            binding.layout.visibility = View.GONE
        }

        binding.imgsave.setOnClickListener{
            saveTransaction()
        }

        binding.imgItem.setOnClickListener {
            binding.layout.visibility = View.VISIBLE
            binding.viewDimBackground.visibility = View.VISIBLE
        }
    }

    private fun saveTransaction(){
        val name = binding.etname.text
        val date = binding.tvdate.text
        val time = binding.tvhour.text
        val note = binding.etnote.text
        val icon = iconTrans
        val background = binding.imgItem.circleBackgroundColor
        val cost = binding.tvnumber.text.toString().toFloat()
        val classify = classifyTrans

        if(binding.tvnumber.text.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                myViewModel.addTransaction(
                    Transaction(0,name.toString(), icon, background.toString(), cost, classify, date.toString(), time.toString(), note.toString())
                )
                withContext(Dispatchers.Main){
                    val intent = Intent(this@InsertActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }else{
            Toast.makeText(this, "Request to enter money", Toast.LENGTH_LONG).show()
        }
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

    @SuppressLint("SetTextI18n")
    fun btnShowCanlender(view: View) {
        binding.calculation.visibility = View.GONE
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        binding.tvdate.text = String.format("%d/%d/%d", day, month + 1, year)

        val datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            binding.tvdate.text = String.format("%d/%d/%d", dayOfMonth, month + 1, year)
        }, year, month, day)
        datePickerDialog.show()
        datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE).apply {
            setTextColor(ContextCompat.getColor(this@InsertActivity, R.color.black))
            text = "Cancel"
        }
        datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.black))
    }

    @SuppressLint("SetTextI18n")
    fun btnShowOClock(view: View) {
        binding.calculation.visibility = View.GONE
        val calender = Calendar.getInstance()
        val hour = calender.get(Calendar.HOUR)
        val minute = calender.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            binding.tvhour.text= String.format(" %d:%d", hourOfDay, minute).toString()
        }, hour, minute, true)
        timePickerDialog.show()
        timePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.black))
        timePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE).apply {
            setTextColor(ContextCompat.getColor(this@InsertActivity, R.color.black))
            text = "Cancel"
        }
    }

    fun showList(view: View) {
        binding.layout.visibility = View.VISIBLE
        binding.calculation.visibility = View.GONE
        binding.viewDimBackground.visibility = View.VISIBLE
    }

    fun showmain(view:View){
        startActivity(Intent(this@InsertActivity, MainActivity::class.java))
        finish()
    }
}



