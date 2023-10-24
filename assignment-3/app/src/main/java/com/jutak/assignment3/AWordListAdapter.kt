package com.jutak.assignment3

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.TypeAItemViewBinding

class AWordListAdapter(
    private val list: List<MyModels.Word>,
    private val context: Context
):RecyclerView.Adapter<AWordListAdapter.TypeAViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAViewHolder {
        val viewholder=TypeAViewHolder(TypeAItemViewBinding.inflate(LayoutInflater.from(parent.context)))
        viewholder.setIsRecyclable(false)
        return viewholder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TypeAViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class TypeAViewHolder(private val binding: TypeAItemViewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: MyModels.Word) {
            val a_word_list = LinearLayout(context).apply {
                val textView1 = TextView(context).apply {
                    text = data.spell.toString()
                    width = resources.getDimension(R.dimen.wordlists_ownerwidth).toInt()
                    height = resources.getDimension(R.dimen.wordlists_height).toInt()
                }
                textView1.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.wordlists_textSize)
                )
                textView1.setTextColor(R.color.black.toInt())
                textView1.setBackgroundResource(R.drawable.bottonborder)
                this.addView(textView1)

                val textView2 = TextView(context).apply {
                    text = data.meaning.toString()
                    width = resources.getDimension(R.dimen.wordlists_namewidth).toInt()
                    height = resources.getDimension(R.dimen.wordlists_height).toInt()
                }
                textView2.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.wordlists_textSize)
                )
                textView2.setTextColor(R.color.black.toInt())
                textView2.setBackgroundResource(R.drawable.bottonborder)
                this.addView(textView2)
            }
            binding.root.addView(a_word_list)
        }
    }
}
