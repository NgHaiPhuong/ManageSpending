package com.example.quan_ly_chi_tieu.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var name : String,
    var icon : String,
    var backgroundColor : String,
    var classify : String,
)