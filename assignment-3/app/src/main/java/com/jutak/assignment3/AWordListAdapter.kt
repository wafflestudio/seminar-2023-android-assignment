package com.jutak.assignment3

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.AWordDialogBinding
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
                    text = data.spell
                    width = resources.getDimension(R.dimen.wordlists_spellwidth).toInt()
                    height = resources.getDimension(R.dimen.wordlists_height).toInt()
                }
                textView1.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.wordlists_textSize)
                )
                textView1.setOnClickListener{makedialog(data)}
                textView1.setTextColor(R.color.black.toInt())
                textView1.setBackgroundResource(R.drawable.bottonborder)
                this.addView(textView1)

                val textView2 = TextView(context).apply {
                    text = data.meaning
                    width = resources.getDimension(R.dimen.wordlists_meaningwidth).toInt()
                    height = resources.getDimension(R.dimen.wordlists_height).toInt()
                }
                textView2.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.wordlists_textSize)
                )
                textView2.setOnClickListener{makedialog(data)}
                textView2.setTextColor(R.color.black.toInt())
                textView2.setBackgroundResource(R.drawable.bottonborder)
                this.addView(textView2)
            }
            binding.root.addView(a_word_list)
        }
        private fun makedialog(data:MyModels.Word){
            val view= AWordDialogBinding.inflate(LayoutInflater.from(context))
            val dialog= AlertDialog.Builder(context)
                .setView(view.root)
                .create()
            view.dialogClose.setOnClickListener {
                dialog.dismiss()
            }
            view.dialogSpell.text=data.spell
            view.dialogMeaning.text=data.meaning
            view.dialogSynonym.text=data.synonym.toString()
            view.dialogAntonym.text=data.antonym.toString()
            view.dialogSentence.text=data.sentence.toString()
            dialog.show()
        }
    }
}
