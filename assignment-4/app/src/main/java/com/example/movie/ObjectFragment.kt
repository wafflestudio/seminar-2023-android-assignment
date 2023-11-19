package com.example.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
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
            val data:MyDataTypes.MovieInfo?=arguments?.getParcelable("object")
            binding.slideText.text = data!!.original_title
            val token :String?= arguments?.getString("token")
            val url = "https://image.tmdb.org/t/p/original/"+data.poster_path
            val glideUrl = GlideUrl(url) { mapOf(Pair("Authorization", "Bearer $token")) }
            Glide.with(view)
                .load(glideUrl)
                .into(binding.slideImage)
        }
    }
}
private const val ARG_OBJECT = "object"