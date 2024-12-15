package com.example.numnewsapp

import android.content.Context
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class VideoCardAdapter(private val videoList: List<VideoCardItemData>): RecyclerView.Adapter<VideoCardAdapter.VideoCardViewHolder>(){
    inner class VideoCardViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val titleVideo : TextView = itemView.findViewById(R.id.titleVideo)
        val video: VideoView = itemView.findViewById(R.id.video)
        val contentVideo : TextView = itemView.findViewById(R.id.contentVideo)
        val  playButton : ImageView = itemView.findViewById(R.id.playButton)
        val cardVideo : CardView = itemView.findViewById(R.id.videoCardView)
//        val thumnail: ImageView = itemView.findViewById(R.id.thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoCardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_items_video, parent, false)
        return VideoCardViewHolder(itemView)
    }

    override fun getItemCount(): Int = videoList.size

    override fun onBindViewHolder(holder: VideoCardViewHolder, position: Int) {
        val itemVideo = videoList[position]

        holder.titleVideo.text = itemVideo.vTitle
        holder.contentVideo.text = itemVideo.vContent



        //Map the video name to a resource ID
        val videoResId = holder.itemView.context.resources.getIdentifier(itemVideo.vNews1, "raw", holder.itemView.context.packageName)



        //Generate a thumbnail for the video
        if (videoResId != 0) {
            val uri = Uri.parse("android.resource://${holder.itemView.context.packageName}/$videoResId")
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(holder.itemView.context, uri)
            val bitmap = retriever.getFrameAtTime(1_000_000)// 1 second into the video
            retriever.release()

            //set the thumbnail
//            holder.thumnail.setImageBitmap(bitmap)
            holder.video.setVideoURI(uri)


            holder.video.setOnPreparedListener{
                holder.playButton.visibility = View.GONE
            }

            holder.video.setOnCompletionListener {
                holder.playButton.setImageResource(R.drawable.baseline_play_circle_filled_24)
                holder.playButton.visibility = View.VISIBLE
            }
            holder.video.setOnClickListener {
                if (holder.video.isPlaying) {
                    holder.video.pause()
                    holder.playButton.setImageResource(R.drawable.baseline_play_circle_filled_24)
                    holder.playButton.visibility = View.VISIBLE
                } else {
                    holder.video.start()
                    holder.playButton.visibility = View.GONE
                }
            }

        } else {
            // Handle the case where the video resource ID is not found
            holder.video.visibility = View.GONE
//            holder.thumnail.setImageResource(R.drawable.ic_launcher_background)
        }

        holder.cardVideo.setOnClickListener{

            val context = holder.itemView.context
            val intent = Intent(context, VideoDetailActivity::class.java)

            // Ensure you're passing the correct data
            intent.putExtra("titleVideo", itemVideo.vTitle)
            intent.putExtra("contentVideo", itemVideo.vContent)
            intent.putExtra("videoDetails", itemVideo.vNews1)

            //Start the VideoDetailActivity

            context.startActivity(intent)
        }

    }


}