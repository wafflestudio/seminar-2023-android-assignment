package com.example.movie

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.movie.databinding.FragmentLoginBinding
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //private val viewModel:MyViewModel by viewModels()
    private lateinit var binding:FragmentLoginBinding
    @Inject
    lateinit var restAPI: MyRestAPI
    private val viewModel:MyViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        /*
        val editor = sharedPreferences.edit()
        editor.putString("token", viewModel.myToken)
        editor.apply()*/
        val token = sharedPreferences.getString("token","default")
        if(token!="default"){
            viewModel.checkAPIKey(token!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            //viewModel.checkAPIKey("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MDA3YTQ0MjU1NGM5ZTNiM2E1NmVhNzQ1MmVkYTNjYiIsInN1YiI6IjY1NTcxODg0ZWE4NGM3MTA5MjI4OTFkNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Tk7NnUpEzXoD-8aaCJw4L-neabtM56D_-WvgB2T50eo")
            viewModel.checkAPIKey(binding.apiKeyInput.text.toString())
        }
        viewModel.error.observe(viewLifecycleOwner) { eventWrapper ->
            eventWrapper.getContentIfNotHandled()?.let { message ->
                Log.d("aaaa","ㄷㄱ객 ㅐㅊ쳣")
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}