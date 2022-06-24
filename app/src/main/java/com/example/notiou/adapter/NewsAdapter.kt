package com.example.notiou.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notiou.R
import com.example.notiou.model.News

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    private var newsList = emptyList<News>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.itemView.findViewById<TextView>(R.id.newsTitle).text = currentItem.title
        holder.itemView.findViewById<TextView>(R.id.newsDate).text = currentItem.published_at
    }

    fun setData(newsList: List<News>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }
}