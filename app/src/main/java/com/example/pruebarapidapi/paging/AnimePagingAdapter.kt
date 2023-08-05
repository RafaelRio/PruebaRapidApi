package com.example.pruebarapidapi.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pruebarapidapi.databinding.AnimeItemBinding
import com.example.pruebarapidapi.models.AnimeItem

class AnimePagingAdapter(
    private val itemClickListener: (item: AnimeItem) -> Unit,
) : PagingDataAdapter<AnimeItem, AnimePagingAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<AnimeItem>() {
        override fun areItemsTheSame(oldItem: AnimeItem, newItem: AnimeItem) = oldItem._id == newItem._id

        override fun areContentsTheSame(oldItem: AnimeItem, newItem: AnimeItem) = oldItem == newItem
    }
) {
    inner class ViewHolder(val binding: AnimeItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimePagingAdapter.ViewHolder, position: Int) {
        val item = getItem(position)

        item?.also {
            holder.itemView.setOnClickListener { itemClickListener(item) }
            holder.binding.tvName.text = item.title
            holder.binding.imvAnime.load(item.image)
        }
    }
}