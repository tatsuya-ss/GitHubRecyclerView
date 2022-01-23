package com.example.githubrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapder(var list: List<GitHubDataStore>): RecyclerView.Adapter<RecyclerViewAdapder.ViewHolder>() {

    class ViewHolder(cell: View): RecyclerView.ViewHolder(cell) {
        val textView: TextView = cell.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.practice_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position].full_name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: List<GitHubDataStore>) {
        this.list = list
    }

}