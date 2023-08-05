package com.example.pruebarapidapi.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebarapidapi.R


class StringAdapter(context: Context, data: ArrayList<String>) :
    RecyclerView.Adapter<StringViewHolder>() {
    private val context: Context
    private val data: ArrayList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.alternative_title_item, parent, false)
        return StringViewHolder(view)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    init {
        this.context = context
        this.data = data
    }
}

class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.tv_alternative_title)
}