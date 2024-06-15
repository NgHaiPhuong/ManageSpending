package com.example.managespending.presentation.home

import android.content.Context
import android.icu.util.Calendar
import android.view.Gravity
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.example.managespending.itemCard
import com.example.managespending.itemHeader
import com.example.managespending.itemInfor
import com.example.managespending.itemTransaction
import com.example.managespending.model.Transaction
import com.example.managespending.nodata
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper

class HomeController : EpoxyController() {
    var listTransaction : MutableList<Transaction> = ArrayList()
        set(value) {
            field.clear()  // Xóa các phần tử hiện có
            field.addAll(value)
            field = value
            requestModelBuild()
        }

    val groupedTransactions = mutableMapOf<String, MutableList<Transaction>>()

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

        if(listTransaction.isEmpty()) {
            nodata {
                id("nodata1")
                spanSizeOverride { totalSpanCount, position, itemCount ->
                    totalSpanCount
                }
            }
        }
        else{
            listTransaction.forEach { transaction ->
                val date = transaction.date
                if (!groupedTransactions.containsKey(date)) {
                    groupedTransactions[date] = mutableListOf()
                }
                groupedTransactions[date]?.add(transaction)
            }

            groupedTransactions.forEach{(date, transaction) ->
                var total : Float = 0F
                transaction.forEach { item->
                    total += item.cost
                }

                itemCard {
                    id("item_card_$date")
                    date(date)
                    total(total.toInt().toString())
                    spanSizeOverride { totalSpanCount, _, _ ->
                        totalSpanCount
                    }
                }
                transaction.forEach { item ->
                    itemInfor {
                        id("item_infor_${item.id}")
                        url(item.icon)
                        name(item.name)
                        cost(item.cost.toInt().toString())
                        spanSizeOverride { totalSpanCount, _, _ ->
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
        return "$day/$month/$year"
    }
}