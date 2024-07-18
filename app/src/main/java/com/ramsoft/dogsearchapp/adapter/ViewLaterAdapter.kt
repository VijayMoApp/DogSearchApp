package com.ramsoft.dogsearchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ramsoft.dogsearchapp.R
import com.ramsoft.dogsearchapp.database.ViewLaterDog
import com.ramsoft.dogsearchapp.databinding.ItemViewLaterImageBinding


class ViewLaterAdapter:  RecyclerView.Adapter<ViewLaterAdapter.ViewLaterViewHolder>() {

    private var items: List<ViewLaterDog> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewLaterViewHolder {
        val binding = ItemViewLaterImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewLaterViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewLaterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<ViewLaterDog>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class ViewLaterViewHolder(private val binding: ItemViewLaterImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ViewLaterDog) {
            binding.dogImageView.load(item.url) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.error)
            }
binding.deleteView
            binding.executePendingBindings()
        }
    }
}