package com.example.managespending.util

import android.annotation.SuppressLint
import java.text.NumberFormat
import java.text.ParseException
import java.util.Locale

object FormatNumberUtil {
    @SuppressLint("ConstantLocale")
    private val numberFormat: NumberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
    fun format(number: Float): String {
        return numberFormat.format(number)
    }
    fun formatWithoutSeparators(formattedNumber: String): String {
        val cleanString = formattedNumber.replace(".", "")
        return try {
            val number = cleanString.toFloat()
            number.toString()
        } catch (e: NumberFormatException) {
            formattedNumber
        }
    }
}