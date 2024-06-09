package com.example.managespending.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.managespending.db.dao.CategoryDao
import com.example.managespending.db.dao.TransactionDao
import com.example.managespending.model.Category
import com.example.managespending.model.Transaction
import com.example.managespending.presentation.home.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Category::class, Transaction::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase(){
    abstract fun categoryDao() : CategoryDao
    abstract fun transactionDao() : TransactionDao

    fun addCategory(category: Category){
        CoroutineScope(Dispatchers.IO).launch {
            categoryDao().insertCategory(category)
        }
    }

    fun addTransaction(transaction: Transaction){
        CoroutineScope(Dispatchers.IO).launch {
            transactionDao().insertTransaction(transaction)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}