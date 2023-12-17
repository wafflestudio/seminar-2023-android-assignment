package com.wafflestudio.assignment4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.wafflestudio.assignment4.databinding.ActivityMainBinding
import com.wafflestudio.assignment4.utils.readFromPreference
import com.wafflestudio.assignment4.views.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigationGraph()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)?.childFragmentManager?.fragments?.get(0)
        if (currentFragment is HomeFragment) {
            if (currentFragment.viewPager.currentItem == 0) {
                super.onBackPressed()
            } else {
                currentFragment.viewPager.currentItem = currentFragment.viewPager.currentItem - 1
            }
        } else {
            super.onBackPressed()
        }
    }

    private fun setNavigationGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val graph = navController.navInflater.inflate(R.navigation.nav_graph)

        if (readFromPreference("token") == null) {
            graph.setStartDestination(R.id.loginFragment)
        } else {
            graph.setStartDestination(R.id.homeFragment)
        }
        navController.graph = graph
    }
}