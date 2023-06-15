package com.linguity.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.linguity.app.R
import com.linguity.app.api.responses.Article
import com.linguity.app.databinding.ItemArticleBinding
import com.linguity.app.ui.english_learning.EnglishLearningDetailActivity

class ArticleAdapter(private val listArticle: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(listArticle[position])
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    inner class ArticleViewHolder(private val itemBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(article: Article) {

            Glide.with(itemView.context)
                .load(article.imageUrl)
                .placeholder(R.drawable.vec_placeholder)
                .centerCrop()
                .into(itemBinding.ivArticleImage)

            itemBinding.tvArticleTitle.apply {
                text = article.title
                if (text.length >= 25) {
                    text = text.replaceRange(text.length - 3, text.length, "…")
                }
            }
            itemView.setOnClickListener {
                Intent(itemView.context, EnglishLearningDetailActivity::class.java).also {
                    it.putExtra(ARTICLE_EXTRA, article)
                    itemView.context.startActivity(it)
                }
            }
        }
    }

    companion object {
        private const val ARTICLE_EXTRA = "article"
    }
}