package com.example.managespending.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.managespending.model.Transaction

@Dao
interface TransactionDao : List<Transaction> {
    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("SELECT * FROM transaction_table")
    fun getAllTransaction() : List<Transaction>

    @Query("SELECT COUNT(*) FROM transaction_table")
    fun quantity() : Int

    @Query("SELECT * FROM transaction_table WHERE date IN (" +
            "SELECT date FROM transaction_table GROUP BY date)")
    fun getTransactionByDate() : List<Transaction>
}