package com.example.managespending.presentation.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyTouchHelper
import com.bumptech.glide.gifdecoder.GifHeader
import com.example.managespending.ItemIncomeBindingModel_
import com.example.managespending.databinding.FragmentIncomeBinding
import com.example.managespending.db.database.MyDatabase
import com.example.managespending.db.viewmodel.MyViewModel
import com.example.managespending.db.viewmodel.MyViewModelFactory
import com.example.managespending.model.Category
import com.example.managespending.presentation.insert.InsertActivity

class IncomeFragment : Fragment() {
    private lateinit var binding : FragmentIncomeBinding
    private lateinit var controller: IncomeController
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var myViewModel: MyViewModel
    private lateinit var listCategory: MutableList<Category>
    private var listIncome : MutableList<Category> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View {
        binding =  FragmentIncomeBinding.inflate(layoutInflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDatabase()
        setupView()
        handleEvent()
    }
    private fun setupDatabase() {
        val dao = MyDatabase.getInstance(requireContext().applicationContext).myDao()
        val factory = MyViewModelFactory(dao)
        myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
    }
    private fun setupView() {
        controller = IncomeController{ icon, name, classify ->
            (requireActivity() as InsertActivity).displayData(icon, name, classify)
        }
        layoutManager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        controller.spanCount = 1
        binding.epoxyincome.layoutManager = layoutManager
        binding.epoxyincome.setControllerAndBuildModels(controller)

        myViewModel.allCategoryList.observe(viewLifecycleOwner){ category ->
            listCategory = category.toMutableList()
            listIncome.clear()
            listCategory.forEach { item ->
                if(item.classify.contains("income", true)){
                    listIncome.add(item)
                }
            }
            controller.listCategory = listIncome
            controller.requestModelBuild()
        }
    }
    private fun handleEvent(){
        val itemDecoration : RecyclerView.ItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.epoxyincome.addItemDecoration(itemDecoration)

        /*  EpoxyTouchHelper.initDragging(controller)
              .withRecyclerView(binding.epoxyincome)
              .forVerticalList()
              .withTarget(ItemIncomeBindingModel_::class.java)
              .andCallbacks(object : EpoxyTouchHelper.DragCallbacks<ItemIncomeBindingModel_>(){
                  override fun onModelMoved(
                      fromPosition: Int,
                      toPosition: Int,
                      modelBeingMoved: ItemIncomeBindingModel_?,
                      itemView: View?
                  ) {
                      controller.listCategory.removeAt(toPosition)
                      controller.requestModelBuild()
                  }
              })*/

        EpoxyTouchHelper.initSwiping(binding.epoxyincome)
            .right()
            .withTarget(ItemIncomeBindingModel_::class.java)
            .andCallbacks(object: EpoxyTouchHelper.SwipeCallbacks<ItemIncomeBindingModel_>(){
                override fun onSwipeCompleted(
                    model: ItemIncomeBindingModel_?,
                    itemView: View?,
                    position: Int,
                    direction: Int
                ) {
                    myViewModel.deleteCategory(listIncome[position])
                    listIncome.removeAt(position)
                    controller.listCategory = listIncome.toMutableList()
                    controller.notifyModelChanged(position)
                    controller.requestModelBuild()
                    Toast.makeText(
                        requireContext(),
                        "Delete successful at position $position",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
    companion object {
        fun newInstance() : IncomeFragment{
            val args = Bundle()
            val fragment : IncomeFragment = IncomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}