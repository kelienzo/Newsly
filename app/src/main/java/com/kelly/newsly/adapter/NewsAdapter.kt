package com.kelly.newsly.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kelly.newsly.databinding.NewsListBinding
import com.kelly.newsly.models.New

class NewsAdapter(var news: List<New>, val click: (New) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: NewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setNewsData(news: New) {
            binding.apply {
                Glide.with(root)
                    .load(news.image)
                    .centerCrop()
                    .into(newsImage)
                newsTitle.text = news.title
                newsAuthor.text = news.author
                newsPublished.text = news.published

                root.setOnClickListener {
                    click(news)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: NewsListBinding = NewsListBinding.inflate(LayoutInflater.from(parent.context))
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.setNewsData(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }
}