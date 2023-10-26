package com.jutak.assignment3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import com.jutak.assignment3.databinding.CreateWordlistDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel:MyViewModel by viewModels()
    private lateinit var adapter:MyMultiAdapter

    @Inject
    lateinit var api:MyRestAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MyMultiAdapter(viewModel.resbody, this)
        binding.wordlists.adapter = adapter
        binding.wordlists.layoutManager = LinearLayoutManager(this)

        viewModel.get_wordlists()
        viewModel.liveresbody.observe(this) {
            Log.d("aaaa", viewModel.liveresbody.value.toString())
            adapter.notifyDataSetChanged()
        }

        binding.createWordlist.setOnClickListener {
            val view = CreateWordlistDialogBinding.inflate(layoutInflater)
            val dialog = AlertDialog.Builder(this)
                .setView(view.root)
                .create()
            view.dialogOk.setOnClickListener {
                if (view.dialogNameinput.text.toString() != "" && view.dialogOwnerinput.text.toString() != "" && view.dialogPwinput.text.toString() != "") {
                    viewModel.postwordlist(
                        MyModels.Data_newlist(
                            view.dialogNameinput.text.toString(),
                            view.dialogOwnerinput.text.toString(),
                            view.dialogPwinput.text.toString()
                        )
                    )
                    dialog.dismiss()
                }
                else{
                    Toast.makeText(this,R.string.toast_required,Toast.LENGTH_SHORT).show()
                }
            }
            view.dialogCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}