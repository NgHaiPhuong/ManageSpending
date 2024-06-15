package com.example.managespending.presentation.insert

import android.content.Context
import android.view.Gravity
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.example.managespending.itemIncome
import com.example.managespending.itemSpend
import com.example.managespending.model.Category
import com.example.managespending.model.Transaction
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper

class InsertController : EpoxyController(){
    var listCategory : MutableList<Category> = ArrayList()
        set(value) {
            field = value
            field.clear()
            field.addAll(value)
            requestModelBuild()
        }

    fun setList(list:MutableList<Category>,category: List<Category>) : List<Category>{
        list.clear()
        list.addAll(category)
        return list
    }
    override fun buildModels() {
        Carousel.setDefaultGlobalSnapHelperFactory(object : Carousel.SnapHelperFactory(){
            override fun buildSnapHelper(context: Context?): SnapHelper {
                return GravitySnapHelper(Gravity.CENTER)
            }
        })

        var count : Int = 0
        listCategory.forEach { item ->
            if(item.classify.contains("spend")){
                itemSpend {
                    id("list_$count")
                    name(item.name)
                    url(item.icon)
                    spanSizeOverride { totalSpanCount, position, itemCount ->
                        totalSpanCount
                    }
                }
                count ++
            }
            else if(item.classify.contains("income")){
                itemIncome {
                    id("list_$count")
                    name(item.name)
                    url(item.icon)
                    spanSizeOverride { totalSpanCount, position, itemCount ->
                        totalSpanCount
                    }
                }
                count++;
            }
        }

    }
}