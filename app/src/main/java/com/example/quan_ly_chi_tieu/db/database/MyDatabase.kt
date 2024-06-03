package com.example.quan_ly_chi_tieu.db.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quan_ly_chi_tieu.db.dao.CategoryDao
import com.example.quan_ly_chi_tieu.db.dao.TransactionDao
import com.example.quan_ly_chi_tieu.model.Category
import com.example.quan_ly_chi_tieu.model.Transaction

@Database(entities = [Category::class, Transaction::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase(){
    abstract fun categoryDao() : CategoryDao
    abstract fun transactionDao() : TransactionDao

    companion object{
        @Volatile
        private var CSDL : MyDatabase? = null
        fun getInstance(context : android.content.Context) : MyDatabase {
            synchronized(this){
                var instance = CSDL
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "MyDatabase"
                    ).build()
                }
                return instance;
            }
        }
    }

}