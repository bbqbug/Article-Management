package com.example.am

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.am.Constants.BUNDLE_NEWS_ID
import com.example.am.databinding.ActivityShowBinding
import com.google.android.material.snackbar.Snackbar

class Show : AppCompatActivity() {
    private lateinit var binding : ActivityShowBinding
    private lateinit var newsEntity: NewsEntity
    private var newsid = 0;
    private var newscontentdf = ""
    private var newstitledf = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intent.extras?.let {
            newsid = it.getInt(BUNDLE_NEWS_ID)
        }


        binding.apply {
            newstitledf=newsDB.dao().getNewsByID(newsid).newsTitle
            newscontentdf=newsDB.dao().getNewsByID(newsid).newsContent
            ETtitle.setText(newstitledf)
            ETcontent.setText(newscontentdf)
            Toast.makeText(this@Show, "$newsid", Toast.LENGTH_LONG).show()
            btndelete.setOnClickListener {
                newsEntity= NewsEntity(newsid,newstitledf,newscontentdf)
                newsDB.dao().deleteNews(newsEntity)
                finish()
            }
            btnsave.setOnClickListener {
                val title = ETtitle.text.toString()
                val desc=ETcontent.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()){

                    newsEntity= NewsEntity(newsid,title,desc)
                    newsDB.dao().updateNews(newsEntity)
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