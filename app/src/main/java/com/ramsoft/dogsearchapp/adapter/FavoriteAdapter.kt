package com.ramsoft.dogsearchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ramsoft.dogsearchapp.R

import com.ramsoft.dogsearchapp.database.FavoriteDog
import com.ramsoft.dogsearchapp.databinding.ItemFavoriteImageBinding


class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var items: List<FavoriteDog> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<FavoriteDog>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(private val binding: ItemFavoriteImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteDog) {
            binding.dogImageView.load(item.url) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.error)
            }
            binding.executePendingBindings()
        }
    }
}