package com.linguity.app.ui.english_learning

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.linguity.app.R
import com.linguity.app.adapter.ArticleAdapter
import com.linguity.app.api.responses.Article
import com.linguity.app.databinding.ActivityEnglishLearningBinding
import com.linguity.app.helper.ViewModelFactory
import com.linguity.app.ui.english_learning.view_model.EnglishLearningViewModel

class EnglishLearningActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityEnglishLearningBinding.inflate(layoutInflater)
    }
    private val viewModel: EnglishLearningViewModel by viewModels {
        ViewModelFactory(this)
    }
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val stringAB: String = resources.getString(R.string.english_learning_page_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = stringAB

        binding.rvArticle.layoutManager = LinearLayoutManager(this)

        viewModel.articleList.observe(this) {
            setDataAdapter(it)
        }
    }

    private fun setDataAdapter(list: List<Article>) {
        articleAdapter = ArticleAdapter(list)

        binding.rvArticle.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = articleAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}