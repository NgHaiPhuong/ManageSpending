package com.example.quan_ly_chi_tieu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transaction (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    var id : Int,
    @ColumnInfo(name = "transaction_name")
    var name : String,
    @ColumnInfo(name = "transaction_icon")
    var icon : Int,
    @ColumnInfo(name = "transaction_background")
    var backgroundColor : String,
    @ColumnInfo(name = "transaction_cost")
    var cost : Float,
    @ColumnInfo(name = "transaction_category")
    var category: String,
    @ColumnInfo(name = "transaction_date")
    var date : String,
    @ColumnInfo(name = "transaction_time")
    var time : String,
    @ColumnInfo(name = "transaction_note")
    var note : String,
)