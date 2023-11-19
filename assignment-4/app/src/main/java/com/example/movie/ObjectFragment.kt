package com.example.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movie.databinding.ActivityScreenSlideBinding

class ObjectFragment : Fragment() {
    private lateinit var binding: ActivityScreenSlideBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=ActivityScreenSlideBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            //val textView: TextView = view.findViewById(android.R.id.text1)
            binding.slideText.text = getInt(ARG_OBJECT).toString()
        }
    }
}
private const val ARG_OBJECT = "object"