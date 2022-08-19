package com.example.myapplicationapi.ui.MainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment(R.layout.fragment_main), SearchView.OnQueryTextListener  {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel by viewModel<MainViewModel>()
    private var myAdapter: MyAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        updateDataInUI()
    }

    // Обновление данных в адаптере по средства Flow
    private fun updateDataInUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.list.collect {
                    myAdapter?.set(it)
                }
            }
        }
    }

    // Иницилизация
    private fun init(){
       myAdapter = MyAdapter{
           // Открытие фрагмента при нажатии на Item
           viewModel.routeToDetail(it)
       }
        with(binding) {
            myRecyclerView.layoutManager = LinearLayoutManager(activity)
            myRecyclerView.adapter = myAdapter
            buttonSort.setOnClickListener {
                //Сортировка при нажатии на кнопку
                viewModel.buttonSortNameOrDesc()
            }
            // Обьявление searchView
            searchViewMain.setOnQueryTextListener(this@MainFragment)
        }
    }

    // Функция для поиска по БД по средством searchView
    private fun searchDatabase(searchQuery: String){
        viewModel.searchDatabase(searchQuery)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }
}
