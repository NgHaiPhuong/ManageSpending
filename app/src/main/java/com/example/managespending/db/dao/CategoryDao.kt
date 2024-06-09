package com.example.managespending.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.managespending.model.Category
import com.example.managespending.model.Transaction

@Dao
interface CategoryDao {
    @Insert
    suspend fun insertCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("DELETE FROM category_table")
    fun deleteAllCategories()

    @Query("SELECT * FROM category_table")
    fun getAllCategory() : List<Category>
}