package com.example.numnewsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

 class RecyclerViewAdapter(
     private var newsList : ArrayList<News>,
     private val context: Context,
     private val onItemClick: (News) -> Unit

 ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemTitle : TextView = itemView.findViewById(R.id.tv_textView)
        val itemContent: TextView = itemView.findViewById(R.id.content)
        val imageView: ImageView = itemView.findViewById(R.id.img)
        val cardView: CardView = itemView.findViewById(R.id.cv_cardView)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_items,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =  newsList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = newsList[position]
        var ViewHolder = holder as ViewHolder
        val resourceId = context.resources.getIdentifier(news.imageUrl, "drawable", context.packageName)

        if(resourceId != 0) {
            ViewHolder.imageView.setImageResource(resourceId)
        }else{
            holder.imageView.setImageResource(R.drawable.img_item1)
        }
        ViewHolder.itemTitle.text = news.title
        ViewHolder.itemContent.text = news.content

        //Handle card click
        ViewHolder.itemView.setOnClickListener{
            onItemClick(news) // pass the full news object to the click listener
        }

        //Truncate the title and content to a maximum of 20 characters
        val maxChars = 20
        val truncatedTitle = if(news.title.length > maxChars) {
            "${news.title.substring(0, maxChars)}..."
        } else {
            news.title
        }

        val truncateContent = if (news.content.length > maxChars) {
            "${news.content.substring(0, maxChars)}..."
        }else {
            news.content
        }

        //Set the truncated text to the TextViews
        ViewHolder.itemTitle.text = truncatedTitle
        ViewHolder.itemContent.text = truncateContent

        ViewHolder.cardView.setOnClickListener{
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("title", news.title) // Pass full title
            intent.putExtra("content", news.content) // Pass full content
            intent.putExtra("imageUrl", news.imageUrl)// Pass image if needed
            context.startActivity(intent)
        }


    }
}