package com.linguity.app.ui.spelling_quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.linguity.app.R
import com.linguity.app.adapter.ItemAdapter
import com.linguity.app.databinding.ActivitySpellingListBinding

class SpellingListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySpellingListBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var stringAB: String = resources.getString(R.string.spelling_quiz_page_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = stringAB
        observeAdapter()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun observeAdapter(){
        val fruits = arrayOf("Apple", "Banana", "Orange", "Mango", "Grapes")
        adapter = ItemAdapter(this@SpellingListActivity, fruits)
        binding.apply {
            rcSpellingList.setHasFixedSize(true)
            rcSpellingList.adapter = adapter
            rcSpellingList.layoutManager = LinearLayoutManager(this@SpellingListActivity)
        }
        adapter.setOnItemClickCallback(object : ItemAdapter.OnItemClickCallback{
            override fun onItemClicked(id: String) {
                Intent(this@SpellingListActivity, SpellingSubmitActivity::class.java).also {
                    it.putExtra(SpellingSubmitActivity.EXTRA_ID, id)
                    startActivity(it)
                }
            }
        })
    }
}