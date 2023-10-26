package com.jutak.assignment3

import android.content.Context
import android.content.Intent
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.TypeAItemViewBinding

class MyMultiAdapter(
    private val list: List<MyModels.Wordlists>,
    private val context: Context
):RecyclerView.Adapter<MyMultiAdapter.TypeAViewHolder>() {
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
                fun bind(data: MyModels.Wordlists) {
                    val a_word_list = LinearLayout(context).apply {
                        val textView1 = TextView(context).apply {
                            text = data.owner
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
                            text = data.name
                            width = resources.getDimension(R.dimen.wordlists_namewidth).toInt()
                            height = resources.getDimension(R.dimen.wordlists_height).toInt()
                        }
                        textView2.setTextSize(
                            TypedValue.COMPLEX_UNIT_PX,
                            resources.getDimension(R.dimen.wordlists_textSize)
                        )
                        textView2.setOnClickListener {
                            Intent(context,DetailActivity::class.java).apply{
                                putExtra("id",data.id)
                                putExtra("name",data.name)

                            }.run{context.startActivity(this)}
                        }
                        textView2.setTextColor(R.color.black.toInt())
                        textView2.setBackgroundResource(R.drawable.bottonborder)
                        this.addView(textView2)
                    }
                    binding.root.addView(a_word_list)
                }
            }
}
