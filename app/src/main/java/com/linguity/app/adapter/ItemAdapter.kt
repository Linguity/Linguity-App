package com.linguity.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.linguity.app.R
import com.linguity.app.api.responses.Quiz
import com.linguity.app.databinding.ItemWordBinding

class ItemAdapter(
    val context: Context,
    private val words: List<Quiz>
) : RecyclerView.Adapter<ItemAdapter.MyHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class MyHolder(val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val (id, text, is_open, is_answered) = words[position]
        val bC = ContextCompat.getColor(context, R.color.linguity_disable)
        holder.apply {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(id.toString())
            }
            binding.apply {
                tvItem.text = text
                if (is_open == 0) {
                    cvItem.isClickable = false
                    cvItem.setCardBackgroundColor(bC)
                }
                if (is_answered == 0) {
                    ivItem.isVisible = false
                }
            }
        }
    }

    override fun getItemCount(): Int = words.size
}