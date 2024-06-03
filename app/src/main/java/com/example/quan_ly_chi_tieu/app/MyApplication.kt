package com.example.quan_ly_chi_tieu.app

import android.app.Application
import com.example.quan_ly_chi_tieu.preference.MyPreferences

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        MyPreferences.init(this@MyApplication)
        //MyPreferences.write(MyPreferences.PREF_COMPLETE_TASKS_TODAY, 1)
        //MyPreferences.read(MyPreferences.PREF_COMPLETE_TASKS_TODAY, 1)
    }
}