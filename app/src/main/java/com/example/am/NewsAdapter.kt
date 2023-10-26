package com.example.am

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.am.Constants.BUNDLE_NEWS_ID
import com.example.am.databinding.ItemnewsBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private lateinit var binding :ItemnewsBinding
    private lateinit var context : Context

//    fun OncreateViewHolder(parent : ViewGroup, type : Int) : ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        binding =ItemnewsBinding.inflate(inflater,  parent, false)
//        context = parent.context
//        return ViewHolder()
//    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding =ItemnewsBinding.inflate(inflater,  parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun getItemCount() : Int {
        return differ.currentList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item : NewsEntity) {
            binding.apply {

                tvTitle.text = item.newsTitle
                tvTitle.textSize = 20.0F
                tvContent.text = item.newsContent
                tvContent.textSize = 10.0F
                root.setBackgroundResource(R.drawable.border)

                root.setOnClickListener {
                    Toast.makeText(context, "${item.newsID}", Toast.LENGTH_LONG).show()
                    val intent= Intent(context, Show::class.java)
                    intent.putExtra(BUNDLE_NEWS_ID, item.newsID)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<NewsEntity>() {
//        fun areItemTheSame(oldItem: NewsEntity, newItem : NewsEntity) : Boolean {
//            return oldItem.newsID == newItem.newsID
//        }
//
//        fun areContentTheSame(oldItem: NewsEntity, newItem : NewsEntity) : Boolean {
//            return oldItem == newItem
//        }

        override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem.newsID == newItem.newsID
        }

        override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem == newItem
        }
    }

        val differ = AsyncListDiffer(this, differCallback)
}