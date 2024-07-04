package com.example.managespending.presentation.insertCategory

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.example.managespending.R
import com.example.managespending.itemInsertcategory
import com.example.managespending.itemList
import com.example.managespending.model.Category
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import yuku.ambilwarna.AmbilWarnaDialog

class InsertCategoryController() : EpoxyController() {
     var listCategory : MutableList<Category> = ArrayList()
        set(value) {
            field.clear()
            field.addAll(value)
            field = value
            requestModelBuild()
        }

    var name : String = ""
    var url : String = ""
    var color : Int = android.graphics.Color.LTGRAY

    override fun buildModels() {
        Carousel.setDefaultGlobalSnapHelperFactory(object :  Carousel.SnapHelperFactory() {
            override fun buildSnapHelper(context: Context?): SnapHelper {
                return GravitySnapHelper(Gravity.CENTER)
            }
        })

        itemInsertcategory {
            id("item1")
            url(this@InsertCategoryController.url)
            name(this@InsertCategoryController.name)
            color(this@InsertCategoryController.color)
            onClick(View.OnClickListener {
                val context = it.context
                this@InsertCategoryController.showColorPickerDialog(context)
                this@InsertCategoryController.requestModelBuild()
            })
            spanSizeOverride { totalSpanCount, position, itemCount ->
                totalSpanCount
            }
        }

        listCategory.forEachIndexed { index, item ->
            itemList {
                id("list$index")
                url(item.icon)
                onClick(View.OnClickListener {
                    this@InsertCategoryController.name = item.name
                    this@InsertCategoryController.url = item.icon
                    this@InsertCategoryController.requestModelBuild()
                })
                spanSizeOverride { totalSpanCount, position, itemCount ->
                    1
                }
            }
        }

    }
    @SuppressLint("SetTextI18n")
    private fun showColorPickerDialog(context: Context){
        val colorPicker = AmbilWarnaDialog(context, color ,object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                this@InsertCategoryController.color = color
                requestModelBuild()
            }

            override fun onCancel(dialog: AmbilWarnaDialog?) {
                // Do nothing
            }
        })
        colorPicker.show()
        colorPicker.dialog.getButton(Dialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(context, R.color.black))
        colorPicker.dialog.getButton(Dialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(context, R.color.black))
        colorPicker.dialog.getButton(Dialog.BUTTON_NEGATIVE)?.text = "Cancel"
    }
}