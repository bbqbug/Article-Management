package com.example.am

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.am.databinding.ActivityAddNewBinding
import com.google.android.material.snackbar.Snackbar

class AddNew : AppCompatActivity() {
    lateinit var binding : ActivityAddNewBinding
    private lateinit var newsEntity: NewsEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnsave.setOnClickListener {
                val title = ETtitle.text.toString()
                val desc = ETcontent.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()){
                    newsEntity= NewsEntity(0,title,desc)
                    newsDB.dao().insertNews(newsEntity)
                    finish()
                }
                else{
                    Snackbar.make(it,"Title and Description cannot be Empty",Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
    private val newsDB : NewsDatabase by lazy {
        Room.databaseBuilder(this, NewsDatabase::class.java, Constants.NEWS_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}