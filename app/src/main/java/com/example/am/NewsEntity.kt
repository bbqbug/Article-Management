package com.example.am

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.am.Constants.NEWS_TABLE

@Entity(tableName = NEWS_TABLE)
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val newsID: Int,
    @ColumnInfo(name ="news_title")
    val newsTitle: String,
    @ColumnInfo(name = "news_content")
    val newsContent: String,

)
