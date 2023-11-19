package com.example.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.movie.databinding.ActivityScreenSlideBinding
import com.example.movie.databinding.FragmentHomeBinding
import com.example.movie.databinding.FragmentLoginBinding
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val NUM_PAGES = 5

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    @Inject
    lateinit var restAPI: MyRestAPI
    //private lateinit var slideAdapter: SlideAdapter
    private val viewModel:MyViewModel by activityViewModels()
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentHomeBinding.inflate(layoutInflater)
        val slideAdapter=SlideAdapter(this,viewModel.myMovieList.value!!)
        binding.pager.adapter=slideAdapter
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //viewPager = bindingPager.pager
        //val pagerAdapter = ScreenSlidePagerAdapter(this as FragmentActivity)
        //viewPager.adapter = pagerAdapter
        //viewModel.getPopularMovies(viewModel.myToken)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.getPopularMovies(viewModel.myToken)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.myMovieList.observe(viewLifecycleOwner){
            (binding.pager.adapter as SlideAdapter)?.notifyDataSetChanged()

            //viewPager = binding.pager
            //viewPager.adapter = pagerAdapter
            //viewPager.offscreenPageLimit = 5
        }
        //screenSlidePagerAdapter=ScreenSlidePagerAdapter(this)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    /*
    private inner class ScreenSlidePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = HomeFragment()
    }*/
}