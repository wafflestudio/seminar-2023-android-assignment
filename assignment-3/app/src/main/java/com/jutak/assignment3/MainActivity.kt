package com.jutak.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordListAdapter()
        binding.recyclerViewWordList.adapter = adapter
        binding.recyclerViewWordList.layoutManager = LinearLayoutManager(this)


        viewModel.fetchWordLists()

        viewModel.wordLists.observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
        val wordListDialog = WordListDialog(viewModel)
        binding.wordListCreateBtn.setOnClickListener {
            wordListDialog.show(supportFragmentManager, "wordListDialogShow")
        }
//        val response = api.getVocaBookList()
//        response.enqueue(object : Callback<List<Vocabook>>{
//            override fun onResponse(call: Call<List<Vocabook>>, response: Response<List<Vocabook>>) {
//                Log.d("success",response.body().toString())
//            }
//
//            override fun onFailure(call: Call<List<Vocabook>>, t: Throwable) {
//                Log.d("fail", t.toString())
//            }
//        })

//        CoroutineScope(Dispatchers.IO).launch {
//            val response = api.getVocaBookListSuspend()
//            withContext(Dispatchers.Main) {
//                binding.text.text = "이름 : ${response[0].id}, 나이 : ${response[0].name}"
//            }
//        }


    }
}