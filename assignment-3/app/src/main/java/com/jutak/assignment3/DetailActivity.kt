package com.jutak.assignment3

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityDetailBinding
import com.jutak.assignment3.databinding.AddWordDialogBinding
import com.jutak.assignment3.databinding.DeleteWordlistDialogBinding
import com.jutak.assignment3.databinding.EditWordlistDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    private val viewModel:MyViewModel by viewModels()
    private lateinit var adapter:AWordListAdapter

    @Inject
    lateinit var api:MyRestAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AWordListAdapter(viewModel.wordlist, this)
        binding.words.adapter = adapter
        binding.words.layoutManager = LinearLayoutManager(this)

        viewModel.curid=intent.getIntExtra("id",0)
        viewModel.curpw=""
        binding.wordlistName.text=intent.getStringExtra("name")

        viewModel.get_Awordlist(viewModel.curid)
        viewModel.livewordlist.observe(this) {
            adapter.notifyDataSetChanged()
        }

        viewModel.livepermission.observe(this){
            when(it){
                true->{
                    binding.editWithPer.visibility=TextView.VISIBLE
                    binding.editNoPer.visibility=TextView.INVISIBLE
                }
                false->{
                    binding.editWithPer.visibility=TextView.INVISIBLE
                    binding.editNoPer.visibility=TextView.VISIBLE
                }
            }
        }

        binding.back.setOnClickListener{back()}

        binding.editNoPer.setOnClickListener {
            val view= EditWordlistDialogBinding.inflate(layoutInflater)
            val dialog= AlertDialog.Builder(this)
                .setView(view.root)
                .create()
            view.dialogOk.setOnClickListener{
                val getpw=view.dialogPwinput.text.toString()
                when(getpw=="") {
                    true->Toast.makeText(this, R.string.toast_required, Toast.LENGTH_SHORT).show()
                    else->{
                        viewModel.pwcorrect(getpw)
                        dialog.dismiss()
                    }
                }
            }
            view.dialogCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        binding.editDel.setOnClickListener {
            val view= DeleteWordlistDialogBinding.inflate(layoutInflater)
            val dialog= AlertDialog.Builder(this)
                .setView(view.root)
                .create()
            view.dialogOk.setOnClickListener{
                viewModel.deletewordlist()
                dialog.dismiss()
                back()
            }
            view.dialogCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        binding.editAdd.setOnClickListener {
            val view= AddWordDialogBinding.inflate(layoutInflater)
            val dialog= AlertDialog.Builder(this)
                .setView(view.root)
                .create()
            view.dialogOk.setOnClickListener{
                if (view.dialogSpellinput.text.toString()!="" && view.dialogMeaninginput.text.toString()!=""){
                    viewModel.addword(MyModels.Word(view.dialogSpellinput.text.toString(),view.dialogMeaninginput.text.toString(),
                        view.dialogSyninput.text.toString(),view.dialogAntinput.text.toString(),view.dialogSeninput.text.toString()))
                    dialog.dismiss()
                }
                else{
                    Toast.makeText(this, R.string.toast_required, Toast.LENGTH_SHORT).show()
                }
            }
            view.dialogCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    private fun back(){
        viewModel.curpermission=false
        viewModel.livepermission.value=viewModel.curpermission
        Intent(this@DetailActivity,MainActivity::class.java).run{
            startActivity(this)
        }
    }
}