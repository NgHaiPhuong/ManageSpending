package com.example.quan_ly_chi_tieu.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quan_ly_chi_tieu.R

class recyclerview_month : RecyclerView.Adapter<MyViewHolder>() {
    val monthList = listOf<String>("Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5",
        "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.listmonth, parent, false)
        return MyViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return monthList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val month = monthList[position]
        holder.bind(month)
    }
}
class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
    fun bind(month: String){
        val textview = view.findViewById<TextView>(R.id.txtmonth)
        textview.text = month
    }
}