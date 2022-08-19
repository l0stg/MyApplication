package com.example.myapplicationapi.ui.router

import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.ui.DetailFragment.DetailFragment
import com.example.myapplicationapi.ui.MainFragment.MainFragment
import com.example.myapplicationapi.ui.imageViewFargment.ImageFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    // Основной фрагмент
    fun routeToMainFragment() = FragmentScreen { MainFragment() }

    // Фрагмент детального просмотра Item'а
    fun routeToDetailFragment(item: DataBaseModel) = FragmentScreen { DetailFragment.newInstance(item) }

    // Фрагмент просмотра изображения
    fun routeToImageFragment(url: String) = FragmentScreen { ImageFragment.newInstance(url) }
}