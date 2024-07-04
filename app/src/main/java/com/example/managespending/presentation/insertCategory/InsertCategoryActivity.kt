package com.example.managespending.presentation.insertCategory

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managespending.R
import com.example.managespending.databinding.ActivityInsertCategoryBinding
import com.example.managespending.databinding.EpoxyItemInsertcategoryBinding
import com.example.managespending.db.database.MyDatabase
import com.example.managespending.db.viewmodel.MyViewModel
import com.example.managespending.db.viewmodel.MyViewModelFactory
import com.example.managespending.model.Category
import com.example.managespending.presentation.category.CategoryFragment
import com.google.android.material.textfield.TextInputEditText
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InsertCategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInsertCategoryBinding
    private lateinit var controller: InsertCategoryController
    private lateinit var layoutManager : GridLayoutManager
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDatabase()
        setupRecyclerView()
        handleEvent()
    }
    private fun setupDatabase() {
        val dao = MyDatabase.getInstance(this).myDao()
        val factory = MyViewModelFactory(dao)
        myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
    }
    private fun setupRecyclerView() {
        controller = InsertCategoryController()
        layoutManager = GridLayoutManager(this, 5, RecyclerView.VERTICAL, false)
        controller.spanCount = 4
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        binding.listCategory.layoutManager = layoutManager
        binding.listCategory.setControllerAndBuildModels(controller)

        myViewModel.allCategoryList.observe(this){categories ->
            controller.listCategory = categories.toMutableList()
            controller.requestModelBuild()
        }
    }
    private fun handleEvent(){
        binding.imgback.setOnClickListener {
            finish()
        }
        binding.imgsave.setOnClickListener {
            val url = controller.url
            val name = findViewById<TextInputEditText>(R.id.tvname).text.toString()
            val color = controller.color.toString()
            Log.d("painting", url + name + color)

            CoroutineScope(Dispatchers.IO).launch {
                myViewModel.addCategory(Category(0, name, url, color, "Spend"))
                withContext(Dispatchers.Main){
                    finish()
                }
            }

        }
    }
}