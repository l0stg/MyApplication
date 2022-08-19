package com.example.myapplicationapi.ui.DetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment() : Fragment(R.layout.fragment_detail) {

    private val viewModel by viewModel<DetailViewModel>()
    private val binding: FragmentDetailBinding by viewBinding()
    private var viewPagerAdapter: ViewPagerAdapter? = null

    // Получение элемента из прошлого фрагмента
    companion object{
        private const val ITEM = "ITEM"
        fun newInstance(item: DataBaseModel) = DetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ITEM, item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Сохарняем элемент в переменную
        val item = arguments?.getSerializable(ITEM) as DataBaseModel
        // Логика создания массива изображений, для отображения в слайдере
        viewModel.addToList(item)
        // Переход на фрагмент просмотра изображения, при нажатии на фото
        viewPagerAdapter = ViewPagerAdapter{
            viewModel.routeToImageFragment(it)
        }
        // Обьявление адаптера и заполнение TextView
        with(binding) {
            myViewPager.adapter = viewPagerAdapter
            tvNameDetail.text = item.name
            tvDescriptionDetail.text = item.description
        }
        // Получение заполненого массива, из ViewModel
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.imageList.collect{
                    viewPagerAdapter?.set(it)
                    // Отображение количества фотографий
                    binding.sizeItem.text = it.count().toString()
                }
            }
        }
    }
}


