package com.example.pruebarapidapi.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebarapidapi.databinding.AnimeItemBinding
import com.example.pruebarapidapi.models.AnimeItem

class AnimeListAdapter : ListAdapter<AnimeItem, AnimeListAdapter.ViewHolder>(
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        item?.also {
            holder.binding.tvName.text = it.title
        }
    }
}