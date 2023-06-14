package com.linguity.app.ui.pronunciation_checker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.linguity.app.R
import com.linguity.app.adapter.ItemAdapter
import com.linguity.app.databinding.ActivityPronunciationListBinding

class PronunciationListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPronunciationListBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val stringAB: String = resources.getString(R.string.pronunciation_page_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = stringAB
        observeAdapter()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun observeAdapter() {
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
}