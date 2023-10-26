package com.example.am

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.am.Constants.NEWS_TABLE

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(newsEntity: NewsEntity)

    @Update
    fun updateNews(newsEntity: NewsEntity)

    @Delete
    fun deleteNews(newsEntity: NewsEntity)

    @Query("select * from $NEWS_TABLE order by newsID Desc")
    fun getAllNews() : MutableList<NewsEntity>

    @Query("select * from $NEWS_TABLE where newsID like :id")
    fun getNewsByID(id : Int) : NewsEntity
}