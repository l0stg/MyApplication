package com.example.myapplicationapi.ui.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigatorHolder: NavigatorHolder by inject()
    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.routeToMain()
    }

    private val navigator = AppNavigator(this, R.id.fragmentContainerView)

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }
}