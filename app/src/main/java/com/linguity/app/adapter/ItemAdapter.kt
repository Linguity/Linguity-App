package com.linguity.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.linguity.app.R
import com.linguity.app.databinding.ItemWordBinding

class ItemAdapter(
    val context: Context,
    private val words: Array<String>) : RecyclerView.Adapter<ItemAdapter.MyHolder>() {

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
        val bC = ContextCompat.getColor(context, R.color.linguity_disable)
        holder.apply {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(words[position])
            }
            binding.apply {
                tvItem.text = words[position]
                if (words[position] != "Apple"){
                    cvItem.isClickable = false
                    cvItem.setCardBackgroundColor(bC)
                    ivItem.isVisible = false
                }
            }
        }
    }

    override fun getItemCount(): Int = words.size
}