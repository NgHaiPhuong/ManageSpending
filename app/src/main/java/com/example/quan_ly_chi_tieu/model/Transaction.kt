package com.example.quan_ly_chi_tieu.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transaction (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var name : String,
    var icon : String,
    var backgroundColor : String,
    var cost : Float,
    var category: String,
    var date : String,
    var time : String,
    var note : String,
)