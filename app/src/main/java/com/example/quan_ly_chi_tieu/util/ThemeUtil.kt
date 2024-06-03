package com.example.quan_ly_chi_tieu.util

import android.content.Context
import com.example.quan_ly_chi_tieu.R
import com.example.quan_ly_chi_tieu.constant.DARK_THEME
import com.example.quan_ly_chi_tieu.constant.LIGHT_THEME
import com.example.quan_ly_chi_tieu.preference.MyPreferences

object ThemeUtil {
    fun getTheme(context: Context): Int {
        val curTheme: Int = MyPreferences.read(MyPreferences.PREF_CURRENT_THEME, LIGHT_THEME)
        when (curTheme) {
            DARK_THEME -> return R.style.Theme_Dark_Theme
            LIGHT_THEME -> return R.style.Theme_Light_Theme
        }
        return R.style.Theme_Light_Theme
    }

    fun getResColor(context: Context, attr: Int): Int {
        var intColor = 0
        try {
            val themeId = getTheme(context)
            val a = context.theme.obtainStyledAttributes(themeId, intArrayOf(attr))
            intColor = a.getColor(0, 0)
            a.recycle()
            val hexColor = Integer.toHexString(intColor)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return intColor
    }

    fun getHexColor(context: Context, attr: Int): String {
        var hexColor = "#FFFFFF"
        try {
            val themeId = getTheme(context)
            val a = context.theme.obtainStyledAttributes(themeId, intArrayOf(attr))
            hexColor = Integer.toHexString(a.getColor(0, 0))
            a.recycle()
            return hexColor
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return hexColor
    }

}