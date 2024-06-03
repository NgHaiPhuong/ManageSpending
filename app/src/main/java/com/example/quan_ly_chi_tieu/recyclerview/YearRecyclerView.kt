package com.example.quan_ly_chi_tieu.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quan_ly_chi_tieu.R

class recyclerview_year : RecyclerView.Adapter<MyViewHolder_1>(){
    val yearList = listOf<String>("2024","2023","2022","2021","2020","2019","2018","2017","2016","2015")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder_1 {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.listyear, parent, false)
        return MyViewHolder_1(listItem)
    }

    override fun getItemCount(): Int {
        return yearList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder_1, position: Int) {
        val year = yearList[position]
        holder.bind(year)
    }
}

class MyViewHolder_1(val view:View) : RecyclerView.ViewHolder(view) {
    fun bind(year:String){
        val textview = view.findViewById<TextView>(R.id.txtyear)
        textview.text = year
    }
}
