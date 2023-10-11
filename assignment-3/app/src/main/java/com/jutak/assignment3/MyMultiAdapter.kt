package com.jutak.assignment3

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.TypeAItemViewBinding

sealed class MyMultiData(val viewType: ViewType){
    data class TypeA(val wordlists: List<MyModels.Wordlists>):MyMultiData(viewType=ViewType.A)
    enum class ViewType {A}
}
class MyMultiAdapter(
    private val list:List<MyMultiData>,
    private val context: Context
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            MyMultiData.ViewType.A.ordinal -> TypeAViewHolder(
                TypeAItemViewBinding.inflate(LayoutInflater.from(parent.context)))
            else -> throw IllegalStateException("Invalid ViewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is MyMultiData.TypeA -> MyMultiData.ViewType.A.ordinal
        }
    }
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]
        when (holder) {
            is TypeAViewHolder -> holder.bind(data as MyMultiData.TypeA)
        }
    }
    inner class TypeAViewHolder(private val binding: TypeAItemViewBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(data: MyMultiData.TypeA){
                    data.wordlists.forEach{
                        val a_word_list=LinearLayout(context).apply{
                            val textView1= TextView(context).apply{
                                text=it.owner.toString()
                                width= resources.getDimension(R.dimen.wordlists_ownerwidth).toInt()
                                height=resources.getDimension(R.dimen.wordlists_height).toInt()
                            }
                            textView1.setTextSize(TypedValue.COMPLEX_UNIT_PX,resources.getDimension(R.dimen.wordlists_textSize))
                            textView1.setTextColor(R.color.black.toInt())
                            textView1.setBackgroundResource(R.drawable.bottonborder)
                            this.addView(textView1)
                            val textView2= TextView(context).apply{
                                text=it.name.toString()
                                width=resources.getDimension(R.dimen.wordlists_namewidth).toInt()
                                height=resources.getDimension(R.dimen.wordlists_height).toInt()
                            }
                            textView2.setTextSize(TypedValue.COMPLEX_UNIT_PX,resources.getDimension(R.dimen.wordlists_textSize))
                            textView2.setTextColor(R.color.black.toInt())
                            textView2.setBackgroundResource(R.drawable.bottonborder)
                            this.addView(textView2)
                        }
                        binding.root.addView(a_word_list)
                    }
                }
            }
}