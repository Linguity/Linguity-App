package com.linguity.app.ui.pronunciation_checker

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.linguity.app.R
import com.linguity.app.adapter.ItemAdapter
import com.linguity.app.api.responses.Quiz
import com.linguity.app.databinding.ActivityPronunciationListBinding
import com.linguity.app.helper.ViewModelFactory
import com.linguity.app.ui.pronunciation_checker.view_model.PronunciationListViewModel

class PronunciationListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPronunciationListBinding.inflate(layoutInflater)
    }
    private val viewModel: PronunciationListViewModel by viewModels {
        ViewModelFactory(this)
    }

    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val stringAB: String = resources.getString(R.string.pronunciation_page_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = stringAB

        val level = intent.getStringExtra(LEVEL_EXTRA)
        level?.let {
            viewModel.getPronunciationListByLevel(it)
        }

        viewModel.pronunciationList.observe(this) {
            observeAdapter(it)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun observeAdapter(quizzes: List<Quiz>) {
        /*
            TODO: Update ItemAdapter to load List<Quiz>
            val listQuiz = quizzes
            adapter = ItemAdapter(this@PronunciationListActivity, listQuiz)
         */
        val fruits = arrayOf("Apple", "Banana", "Orange", "Mango", "Grapes")
        adapter = ItemAdapter(this@PronunciationListActivity, fruits)
        binding.apply {
            rcPronunciationList.setHasFixedSize(true)
            rcPronunciationList.adapter = adapter
            rcPronunciationList.layoutManager = LinearLayoutManager(this@PronunciationListActivity)
        }
        adapter.setOnItemClickCallback(object : ItemAdapter.OnItemClickCallback {
            override fun onItemClicked(id: String) {
                Intent(
                    this@PronunciationListActivity,
                    PronunciationSubmitActivity::class.java
                ).also {
                    it.putExtra(PronunciationSubmitActivity.EXTRA_ID, id)
                    startActivity(it)
                }
            }
        })
    }

    companion object {
        private const val LEVEL_EXTRA = "level"
    }
}