package com.example.managespending.db.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managespending.db.dao.MyDao
import com.example.managespending.model.Category
import com.example.managespending.model.Transaction
import kotlinx.coroutines.launch

class MyViewModel(private val dao: MyDao) : ViewModel(){
    val allCategoryList = dao.getAllCategory()
    val allTransactionList = dao.getAllTransaction()

    fun addCategory(newItem : Category) = viewModelScope.launch {
        dao.insertCategory(newItem)
    }

    fun addTransaction(newItem:Transaction) = viewModelScope.launch {
        dao.insertTransaction(newItem)
    }

    fun addAllCategory(newList : List<Category>) = viewModelScope.launch {
        dao.insertAllCategory(newList)
    }

    fun deleteCategory() = viewModelScope.launch {
        dao.deleteAllCategories()
    }

    fun deleteTransaction() = viewModelScope.launch {
        dao.deleteAllTransaction()
    }
}