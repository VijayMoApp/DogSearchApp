package com.ramsoft.dogsearchapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ramsoft.dogsearchapp.R
import com.ramsoft.dogsearchapp.databinding.ItemDogImageBinding


class BreedsRecyclerViewAdapter(
    private val onFavoriteClicked: (String) -> Unit ,
    private val onViewLaterClicked: (String) -> Unit,
    private val onShareClicked: (String) -> Unit,
    private val onSaveClicked: (String) -> Unit
) : RecyclerView.Adapter<BreedsRecyclerViewAdapter.DogImageViewHolder>() {

    private var images: List<String> = emptyList()


    fun updateImages(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageViewHolder {
        val binding = ItemDogImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogImageViewHolder(binding)
    }




    override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {
        val imageUrl = images[position]
        holder.bind(imageUrl)
    }

    override fun getItemCount() = images.size




    inner class DogImageViewHolder(private val binding: ItemDogImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {

            binding.dogImageView.load(imageUrl) {
                placeholder(R.drawable.placeholder) // Optional placeholder image
                error(R.drawable.error) // Optional error image
            }
            binding.favoriteIcon.setOnClickListener {
                onFavoriteClicked(imageUrl)
            }

            binding.viewlaterIcon.setOnClickListener {
                onViewLaterClicked(imageUrl)
            }

            binding.shareIcon.setOnClickListener {
                onShareClicked(imageUrl)
            }

            binding.saveIcon.setOnClickListener {
                onSaveClicked(imageUrl)
            }
        }
    }
}

