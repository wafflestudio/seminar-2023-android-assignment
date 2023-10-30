package com.jutak.assignment3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.CustomDialogLayoutBinding
import com.jutak.assignment3.databinding.WordListBinding
import com.jutak.assignment3.databinding.WordListInfoBinding

class WordListAdapter<T: MyData>(private val context:Context, private var items: MutableList<T>, private val itemClickListener: OnItemClickListener<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItems(newItems: MutableList<T>) {
        items = newItems
        notifyDataSetChanged()
    }

    private fun showItemDialog(item: MyData.WordInfo) {
        val customLayoutBinding = CustomDialogLayoutBinding.inflate(LayoutInflater.from(context))

        // 다이얼로그 생성 및 커스텀 레이아웃 설정
        val alertDialog = AlertDialog.Builder(context)
            .setView(customLayoutBinding.root)
            .create()

        // 다이얼로그 내용 설정
        customLayoutBinding.dialogText.text = "Spell: ${item.spell}" +
                "\nMeaning: ${item.meaning}" +
                "\nSynonym: ${item.synonym.takeIf { it != null }}" +
                "\nAntonym: ${item.antonym.takeIf { it != null }}" +
                "\nSentence: ${item.sentence.takeIf { it != null }}"

        // Positive Button 클릭 리스너 설정
        customLayoutBinding.positiveButton.setOnClickListener {
            // Positive Button을 눌렀을 때의 동작을 여기에 추가
            alertDialog.dismiss() // 다이얼로그 닫기
        }
        // 다이얼로그 표시
        alertDialog.show()
    }

    inner class WordListInfoViewHolder(private val binding: WordListInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyData.WordListInfo) {
            binding.wordListOwner.text = item.owner
            binding.wordListName.text = item.name
        }
    }

    inner class WordListViewHolder(private val binding: WordListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyData.WordInfo) {
            binding.wordSpell.text = item.spell
            binding.wordMeaning.text = item.meaning
            binding.root.setOnClickListener {
                showItemDialog(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            MyData.ViewType.WORD_LIST_INFO.ordinal -> {
                val binding = WordListInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                WordListInfoViewHolder(binding)
            }
            MyData.ViewType.WORD_INFO.ordinal -> {
                val binding = WordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                WordListViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WordListAdapter<*>.WordListInfoViewHolder -> {
                val item = items[position] as MyData.WordListInfo
                val layoutParams = holder.itemView.layoutParams
                layoutParams.height = 100
                holder.itemView.requestLayout()
                holder.bind(item)
                holder.itemView.setOnClickListener {
                    itemClickListener.onItemClick(item.id) // 아이템의 ID를 전달
                }
            }
            is WordListAdapter<*>.WordListViewHolder -> {
                val item = items[position] as MyData.WordInfo
                val layoutParams = holder.itemView.layoutParams
                layoutParams.height = 120
                holder.itemView.requestLayout()
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MyData.WordListInfo-> MyData.ViewType.WORD_LIST_INFO.ordinal
            is MyData.WordInfo -> MyData.ViewType.WORD_INFO.ordinal
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }
}