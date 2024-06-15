package com.example.managespending.db.database

import com.example.managespending.model.Category

object GetList{
    fun addData() : List<Category>{
        return arrayListOf(
            Category(1, "Build", "spend/build.png", "#ffffff", "spend"),
            Category(2, "Car", "spend/car.png", "#ffffff", "spend"),
            Category(3, "Cinema", "spend/cinema.png", "#ffffff", "spend"),
            Category(4, "Clothes", "spend/clothes.png", "#ffffff", "spend"),
            Category(5, "Drink", "spend/drink.png", "#ffffff", "spend"),

            Category(6, "Coupon", "income/coupon.png", "#ffffff", "income"),
            Category(7, "Discount", "income/discount.png", "#ffffff", "income"),
            Category(8, "Salary", "income/salary.png", "#ffffff", "income"),
        )
    }
}