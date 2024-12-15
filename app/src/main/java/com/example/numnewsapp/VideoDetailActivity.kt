package com.example.numnewsapp

import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VideoDetailActivity : AppCompatActivity() {
    private var isPlaying = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_video_detail)


        val arrow_back: ImageView = findViewById(R.id.arrow_back)

        arrow_back.setOnClickListener{
            onBackPressed()
        }

        // Get data from the intent
        val videoTitle = intent.getStringExtra("titleVideo") ?: "Default Title"
        val videoContent = intent.getStringExtra("contentVideo") ?: "No Content Available"
        val videoName = intent.getStringExtra("videoDetails")
        // Find views
        val titleTextView: TextView = findViewById(R.id.titleVideo)
        val contentTextView: TextView = findViewById(R.id.contentVideo)
        val videoView: VideoView = findViewById(R.id.videoDetailsPage)
        val playPauseButton: ImageView = findViewById(R.id.playPauseImageView)
//        val btnPlay: ImageView = findViewById(R.id.playPauseImageView)

        // Set data to views
        titleTextView.text = videoTitle
        contentTextView.text = videoContent

        // Play video from raw folder
        val videoResId = resources.getIdentifier(videoName, "raw", packageName)
        if (videoResId != 0) {
            val uri = Uri.parse("android.resource://$packageName/$videoResId")
            // Log the video details for debugging
            Log.d("VideoDetailActivity", "Video Name: $videoName")
            Log.d("VideoDetailActivity", "Video Resource ID: $videoResId")
            Log.d("VideoDetailActivity", "Video URI: $uri")

            videoView.setVideoURI(uri)
            videoView.start()

            // Prepare the video
            videoView.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.isLooping = false // Disable looping
                playPauseButton.setImageResource(R.drawable.baseline_play_circle_filled_24) // Set icon to "play"
                videoView.pause() // Explicitly pause to prevent auto-start
            }

            // Handle play/pause toggle on button click
            playPauseButton.setOnClickListener {
                if (isPlaying) {
                    videoView.pause()
                    playPauseButton.setImageResource(R.drawable.baseline_play_circle_filled_24)
                } else {
                    videoView.start()
                    playPauseButton.setImageResource(R.drawable.baseline_pause_circle_24)
                }
                isPlaying = !isPlaying
            }

            // Optional: Handle video completion
            videoView.setOnCompletionListener {
                isPlaying = false
                playPauseButton.setImageResource(R.drawable.baseline_play_circle_filled_24)
            }

        }


        }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    }

