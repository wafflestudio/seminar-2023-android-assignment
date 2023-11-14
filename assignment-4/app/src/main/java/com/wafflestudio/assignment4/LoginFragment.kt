package com.wafflestudio.assignment4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.wafflestudio.assignment4.databinding.FragmentLoginBinding
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.button.setOnClickListener(){
            var key : String? = binding.username.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    viewModel.login(key)
                } catch(e : HttpException){
                    Toast.makeText(context, e.response()?.errorBody()?.string(), Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.token.observe(viewLifecycleOwner){
            if(MyApplication.preferences.getToken("success", "")=="true") {
                findNavController().navigate(R.id.action_loginFragment_to_blankFragment)
            }
        }

        return binding.root
    }
}