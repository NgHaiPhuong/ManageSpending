package com.example.quan_ly_chi_tieu.util

import android.content.Context
import com.example.quan_ly_chi_tieu.constant.DEFAULT_LANGUAGE
import com.example.quan_ly_chi_tieu.constant.LIGHT_THEME
import com.example.quan_ly_chi_tieu.preference.MyPreferences
import java.util.Locale

object LanguageUtil {
    fun setLanguage(context: Context?) {
        if (context == null) return
        var language: String? = MyPreferences.read(MyPreferences.PREF_CURRENT_LANGUAGE, DEFAULT_LANGUAGE)
        if (language == null) {
            language = Locale.getDefault().language
        }
        val newLocale = Locale(language!!.lowercase(Locale.getDefault()))
        Locale.setDefault(newLocale)
        val res = context.resources
        val conf = res.configuration
        //        conf.locale = newLocale;
//        res.updateConfiguration(conf, res.getDisplayMetrics());
        conf.setLocale(newLocale)
        res.updateConfiguration(conf, res.displayMetrics)
    }

    fun getLanguageName(context: Context) : String {
        return try {
            var language = MyPreferences.read(MyPreferences.PREF_CURRENT_LANGUAGE, DEFAULT_LANGUAGE)
            if (language == null) {
                language = Locale.getDefault().language
            }
            val newLocal = Locale(language.toString().lowercase())
            Locale.setDefault(newLocal)
            newLocal.displayName
        } catch (e: Exception) {
            ""
        }
    }
}