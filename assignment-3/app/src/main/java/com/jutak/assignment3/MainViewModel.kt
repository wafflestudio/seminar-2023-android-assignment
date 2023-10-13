package com.jutak.assignment3

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jutak.assignment3.databinding.AddDialogLayoutBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: MyRestAPI
): ViewModel() {
    private val _wordListsLiveData = MutableLiveData<MutableList<WordListsInfo>>(mutableListOf())
    val wordListsLiveData: LiveData<MutableList<WordListsInfo>> = _wordListsLiveData
    var wordListsInfo: MutableList<WordListsInfo> = mutableListOf()

    fun getWordListsInfo(){
        viewModelScope.launch(Dispatchers.IO){
            val response = api.getWordListsInfoSuspend()
            withContext(Dispatchers.Main) {
                _wordListsLiveData.value!!.addAll(response)
                wordListsInfo.addAll(response)
                Log.d(wordListsInfo.toString(),"asdifj") //잘 나옴
            }
        }
    }
    fun showAlertDialog(context: Context) {
        val alertDialog = AlertDialog.Builder(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.add_dialog_layout, null)
        val binding = AddDialogLayoutBinding.inflate(LayoutInflater.from(context))
        alertDialog.setTitle("제목")
        alertDialog.setView(dialogView)

        alertDialog.setPositiveButton("확인") { dialog, which ->
            //addWordList(binding.name.text.toString(),binding.owner.text.toString(),binding.pw.text.toString())
        }
        alertDialog.setNegativeButton("취소") { dialog, which ->
            // 취소 버튼을 눌렀을 때 수행할 작업
        }
        alertDialog.show()
    }
    /*fun addWordList(name:String, owner:String, pw:String){
        viewModelScope.launch(Dispatchers.IO){
            val response = api.postWordListSuspend(WordListPost(name,owner,pw))
            withContext(Dispatchers.Main) {
                _wordListsLiveData.value!!.add(response)
                wordListsInfo.add(response)
            }
        }
    }*/
}