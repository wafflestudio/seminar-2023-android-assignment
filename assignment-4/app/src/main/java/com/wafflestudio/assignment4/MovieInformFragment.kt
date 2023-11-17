package com.wafflestudio.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wafflestudio.assignment4.databinding.FragmentMovieBinding

private const val ARG_OBJECT = "object"
class MovieInformFragment:Fragment() {

    private lateinit var binding:FragmentMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val textView: TextView = binding.text1
            textView.text = getInt(ARG_OBJECT).toString()
        }
    }
}

class MovieInformAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = 5
    override fun createFragment(position: Int): Fragment {
        val fragment = MovieInformFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT,position+1)
        }
        return fragment
    }
}