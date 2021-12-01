package com.mohdroid.zarinpalcodechallenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mohdroid.zarinpalcodechallenge.databinding.ActivityMainBinding
import com.mohdroid.zarinpalcodechallenge.features.common.ActParent
import com.mohdroid.zarinpalcodechallenge.features.common.RootApp
import com.mohdroid.zarinpalcodechallenge.features.common.viewmodel.ViewModelFactory
import javax.inject.Inject


class MainActivity : ActParent<MainViewModel>() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frgNavHost) as NavHostFragment
        NavigationUI.setupWithNavController(binding.activityMainBottomNavigationView, navHostFragment.navController)
    }

    override fun getFactory(): ViewModelProvider.Factory = factory

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun inject() {
        (applicationContext as RootApp).appGraph.inject(this)
    }
}