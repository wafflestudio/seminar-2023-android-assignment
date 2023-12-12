package com.example.movie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.movie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var restAPI: MyRestAPI

    private val viewModel:MyViewModel by viewModels()

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val sharedPreferences = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        /*
        val editor = sharedPreferences.edit()
        editor.putString("token", viewModel.myToken)
        editor.apply()*/
        val token = sharedPreferences.getString("token","default")
        if(token!="default"){
            viewModel.myToken=token!!
            navController.navigate(R.id.homeFragment)
        }
        else navController.navigate(R.id.loginFragment)
        //viewModel.printOne()
        viewModel.error.observe(this) { eventWrapper ->
            eventWrapper.getContentIfNotHandled()?.let {
                Log.d("aaaa","ㄷㄱ객 ㅐㅊ쳣")
                Toast.makeText(this,"wrong token",Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.transition.observe(this) { eventWrapper ->
            eventWrapper.getContentIfNotHandled()?.let {
                navController.navigate(R.id.action_loginFragment_to_homeFragment)
                Log.d("aaaa",navController.currentBackStackEntry.toString())
            }
        }
        viewModel.logout.observe(this) { eventWrapper ->
            eventWrapper.getContentIfNotHandled()?.let {

                val editor = sharedPreferences.edit()
                editor.putString("token", "default")
                editor.apply()
                //navController.popBackStack(R.id.loginFragment, false)
                navController.navigate(R.id.action_homeFragment_to_loginFragment2)
                Log.d("aaaa",navController.currentBackStackEntry.toString())
            }
        }
    }
    fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
    }

    fun NavController.safeNavigate(
        @IdRes currentDestinationId: Int,
        @IdRes id: Int,
        args: Bundle? = null
    ) {
        if (currentDestinationId == currentDestination?.id) {
            navigate(id, args)
        }
    }
}