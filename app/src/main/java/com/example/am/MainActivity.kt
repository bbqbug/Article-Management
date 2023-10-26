package com.example.am

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.am.Constants.NEWS_DATABASE
import com.example.am.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnadd.setOnClickListener {
            startActivity(Intent(this, AddNew::class.java))
        }

    }
    private val newsDB : NewsDatabase by lazy {
        Room.databaseBuilder(this,NewsDatabase::class.java,NEWS_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private val newsAdapter by lazy { NewsAdapter() }

    override fun onResume() {
        super.onResume()
        checkItem()
    }
    private fun checkItem(){
        binding.apply {
            if(newsDB.dao().getAllNews().isNotEmpty()){
                rvList.visibility= View.VISIBLE
                tvempty.visibility=View.GONE
                //reset list
                newsAdapter.differ.submitList(null)
                newsAdapter.differ.submitList(newsDB.dao().getAllNews())
                setupRecyclerView()
            }else{
                rvList.visibility=View.GONE
                tvempty.visibility=View.VISIBLE
            }
        }
    }
    private fun setupRecyclerView(){
        binding.rvList.apply {
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=newsAdapter
        }
    }
}