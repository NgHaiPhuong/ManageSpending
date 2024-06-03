package com.example.quan_ly_chi_tieu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    var id : Int,
    @ColumnInfo(name = "category_name")
    var name : String,
    @ColumnInfo(name = "category_icon")
    var icon : Int,
    @ColumnInfo(name = "category_background")
    var backgroundColor : String,
    @ColumnInfo(name = "category_classify")
    var classify : String,
)