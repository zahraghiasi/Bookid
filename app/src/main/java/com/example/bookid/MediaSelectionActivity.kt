package com.example.bookid

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class MediaSelectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_selection)

        val backButton = findViewById<ImageButton>(R.id.back_btn)
        backButton.setOnClickListener {
            startActivity(Intent(this, ShazdeCollectionsActivity::class.java))
            finish();
        }

        val arButton = findViewById<ImageButton>(R.id.ar_option)
        arButton.setOnClickListener {
            startActivity(Intent(this, ARCameraActivity::class.java))
            finish();
        }

        val podcastButton = findViewById<ImageButton>(R.id.padcast_option)
        podcastButton.setOnClickListener {
            startActivity(Intent(this, PodcastActivity::class.java))
            finish();
        }

        val gameButton = findViewById<ImageButton>(R.id.game_option)
        gameButton.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
            finish();
        }


    }
}