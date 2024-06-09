package com.example.managespending.presentation.home

import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Gravity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.SnapHelper
import androidx.room.Query
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.example.managespending.R
import com.example.managespending.db.dao.CategoryDao
import com.example.managespending.db.dao.TransactionDao
import com.example.managespending.itemCard
import com.example.managespending.itemHeader
import com.example.managespending.itemInfor
import com.example.managespending.itemTransaction
import com.example.managespending.model.Transaction
import com.example.managespending.nodata
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import java.sql.Date

class HomeController : EpoxyController() {
    var listTransactionByDate : List<Transaction> = ArrayList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var listTransaction : List<Transaction> = ArrayList()

    override fun buildModels() {
        Carousel.setDefaultGlobalSnapHelperFactory(object : Carousel.SnapHelperFactory(){
            override fun buildSnapHelper(context: Context?): SnapHelper {
                return GravitySnapHelper(Gravity.CENTER)
            }
        })

        itemHeader {
            id("item_header")
            date(this@HomeController.getDate())
            spanSizeOverride { totalSpanCount, position, itemCount ->
                totalSpanCount
            }
        }

        itemTransaction {
            id("item_transaction")
            balance("1.000.000")
            expand("150.000")
            income("2.000.000")
            arms("300.000")
            spanSizeOverride { totalSpanCount, position, itemCount ->
                totalSpanCount
            }
        }

        if(listTransaction.isEmpty()){
            nodata {
                id("nodat1")
                spanSizeOverride { totalSpanCount, position, itemCount ->
                    totalSpanCount
                }
            }
        }
        else{
            listTransactionByDate.forEach{ item->
                itemCard {
                    id("item_card_1")
                    date(item.date)
                    total("-100.000")
                    spanSizeOverride { totalSpanCount, position, itemCount ->
                        totalSpanCount
                    }
                }
                listTransactionByDate.forEach { item->
                    itemInfor {
                        id("item" + item.id)
                        url(item.icon)
                        name(item.name)
                        cost(item.cost.toString())
                        spanSizeOverride { totalSpanCount, position, itemCount ->
                            totalSpanCount
                        }
                    }
                }

            }
        }

    }

    fun getDate() : String{
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return "$month/$day/$year"
    }
}