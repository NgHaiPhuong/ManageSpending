package com.example.managespending.presentation.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.managespending.R
import com.example.managespending.base.BaseActivity
import com.example.managespending.databinding.ActivityDetailBinding
import com.example.managespending.db.database.MyDatabase
import com.example.managespending.db.viewmodel.MyViewModel
import com.example.managespending.db.viewmodel.MyViewModelFactory
import com.example.managespending.model.Transaction
import com.example.managespending.presentation.home.HomeFragment
import com.example.managespending.util.FormatNumberUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class DetailActivity : BaseActivity() {
    private lateinit var binding : ActivityDetailBinding
    private var transactionId : Int = 0
    private lateinit var myViewModel: MyViewModel
    private var listTransaction : MutableList<Transaction> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setupDatabase()
        initData()
        handleEvent()
    }

    private fun setupDatabase() {
        val dao = MyDatabase.getInstance(application).myDao()
        val factory = MyViewModelFactory(dao)
        myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
    }

    private fun initData() {
        transactionId = intent.getIntExtra("id", 0)

        myViewModel.allTransactionList.observe(this) { transactions ->
            listTransaction.clear()
            listTransaction = transactions.toMutableList()
        }

        binding.tvname.setText(intent.getStringExtra("name"))
        val cost =  intent.getFloatExtra("cost", 0f)
        binding.tvcost.setText(FormatNumberUtil.format(cost))
        binding.etcategory.setText(intent.getStringExtra("category"))
        binding.tvdate.text = intent.getStringExtra("date")
        binding.etnote.setText(intent.getStringExtra("note"))
        binding.url = intent.getStringExtra("url")
        binding.executePendingBindings()
    }

    private fun handleEvent() {
        binding.imgback.setOnClickListener {
            startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            finish()
        }
        binding.btnedit.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Confirm update transaction")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes"){dialog, which->
                    val name = binding.tvname.text.toString()
                    val cost = FormatNumberUtil.formatWithoutSeparators(binding.tvcost.text.toString())
                    val category = binding.etcategory.text.toString()
                    val date = binding.tvdate.text.toString()
                    val note = binding.etnote.text.toString()
                    val time = intent.getStringExtra("time").toString()
                    listTransaction.forEach {item->
                        if(item.id.equals(transactionId)){
                            item.name = name
                            item.cost = cost.toFloat()
                            item.category = category
                            item.date = date
                            item.note = note
                           // item.icon = item.icon
                            item.time = time
                        }
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        myViewModel.deleteAllTransaction()
                        myViewModel.addAllTransaction(listTransaction)
                        withContext(Dispatchers.Main){
                            val intent = Intent(this@DetailActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                .setNegativeButton("No"){dialog, which->
                    dialog.dismiss()
                }.create()
            alertDialog.show()
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(
                ContextCompat.getColor(this, R.color.black)
            )
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(
                ContextCompat.getColor(this, R.color.black)
            )
        }
        binding.imgdelete.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Confirm delete transaction")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes") { dialog, which ->
                    val itemsToRemove = mutableListOf<Transaction>()
                    listTransaction.forEach { item ->
                        if (item.id == transactionId) {
                            itemsToRemove.add(item)
                        }
                    }
                    listTransaction.removeAll(itemsToRemove)
                    itemsToRemove.forEach { item ->
                        Log.d(
                            "DeleteTransaction",
                            "Deleting transaction with id: ${item.name} + ${item.id}"
                        )
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        myViewModel.deleteAllTransaction()
                        myViewModel.addAllTransaction(listTransaction)
                        withContext(Dispatchers.Main) {
                            val intent = Intent(this@DetailActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                .setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                .create()
            alertDialog.show()
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(
                ContextCompat.getColor(this, R.color.black)
            )
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(
                ContextCompat.getColor(this, R.color.black)
            )
        }
    }

    @SuppressLint("DefaultLocale")
    fun btnShowCanlender(view: View) {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            binding.tvdate.text = String.format("%d/%d/%d", dayOfMonth, month + 1, year)
        },  year, month, day)
        datePickerDialog.show()
        datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE).apply {
            setTextColor(ContextCompat.getColor(this@DetailActivity, R.color.black))
            text = "Cancel"
        }
        datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.black))
    }
}