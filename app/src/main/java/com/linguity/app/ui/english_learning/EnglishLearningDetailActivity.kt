package com.linguity.app.ui.english_learning

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.linguity.app.R
import com.linguity.app.api.responses.Article
import com.linguity.app.databinding.ActivityEnglishLearningDetailBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class EnglishLearningDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityEnglishLearningDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val stringAB: String = resources.getString(R.string.english_learning_page_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = stringAB

        val article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(ARTICLE_EXTRA, Article::class.java)
        } else {
            intent.getParcelableExtra(ARTICLE_EXTRA)
        }

        article?.let {
            loadArticle(article)
        }
    }

    private fun loadArticle(article: Article) {
        Glide.with(this)
            .load(article.imageUrl)
            .placeholder(R.drawable.vec_placeholder)
            .centerCrop()
            .into(binding.ivDetailImage)

        binding.tvDetailTitle.text = article.title

        binding.tvDetailContent.text = Html.fromHtml(
            article.content?.let { getHtmlFromUrl(it) },
            Html.FROM_HTML_MODE_LEGACY
        )
    }

    private fun getHtmlFromUrl(urlString: String): String {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.connectTimeout = 10000
        connection.readTimeout = 10000

        try {
            val inputStream = connection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?

            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }

            return stringBuilder.toString()
        } finally {
            connection.disconnect()
        }
    }

    companion object {
        private const val ARTICLE_EXTRA = "article"
    }
}