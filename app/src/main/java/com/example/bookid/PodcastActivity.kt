package com.example.bookid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.media.AudioManager
import android.media.MediaPlayer
import android.widget.Toast

class PodcastActivity : ComponentActivity() {

    private lateinit var imageView: ImageView
    private lateinit var pageNumberTextView: TextView
    private lateinit var textView: TextView
    private lateinit var nextButton: ImageButton
    private lateinit var preButton: ImageButton

    lateinit var playIB: ImageButton
    lateinit var pauseIB: ImageButton
    lateinit var mediaPlayer: MediaPlayer

    private var currentPage = 0

    // Data for each page (You can load this data dynamically from a server or database if needed)
    private val pages = listOf(
        PageData(R.drawable.page1, "۱", " شازده فرفره گفت:(چرا من باید وقتی که خسته نیستم بخوابم و وقتی که خسته\u200Cام از خواب بیدار بشوم؟)"),
        PageData(R.drawable.page2, "۲", "او گفت:(من نمی‌خواهم بروم بخوابم!)"),
        PageData(R.drawable.page3, "۳", "(من یک لیوان آب می‌خواهم!)")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podcast)

        val backButton = findViewById<ImageButton>(R.id.back_btn)
        backButton.setOnClickListener {
            startActivity(Intent(this, MediaSelectionActivity::class.java))
            finish();
        }

        imageView = findViewById(R.id.imageView)
        pageNumberTextView = findViewById(R.id.pageNumberTextView)
        textView = findViewById(R.id.textView)
        nextButton = findViewById(R.id.nextButton)
        preButton = findViewById(R.id.preButton)

        // Initialize the first page
        updatePage()

        nextButton.setOnClickListener {
            currentPage = (currentPage + 1) % pages.size
            updatePage()
        }
        preButton.setOnClickListener {
            currentPage = (currentPage - 1) % pages.size
            updatePage()
        }
        // on below line we are initializing
        // our buttons with id.
        playIB = findViewById(R.id.play_btn)
        pauseIB = findViewById(R.id.pouse_btn)

        // on below line we are
        // initializing our media player
        mediaPlayer = MediaPlayer()


        playIB.setOnClickListener {

            playIB.visibility = Button.INVISIBLE
            pauseIB.visibility = Button.VISIBLE

            // on below line we are creating a variable for our audio url
            var audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

            // on below line we are setting audio stream
            // type as stream music on below line.
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

            // on below line we are running a try
            // and catch block for our media player.
            try {
                // on below line we are setting audio
                // source as audio url on below line.
                mediaPlayer.setDataSource(audioUrl)

                // on below line we are
                // preparing our media player.
                mediaPlayer.prepare()

                // on below line we are
                // starting our media player.
                mediaPlayer.start()

            } catch (e: Exception) {

                // on below line we are handling our exception.
                e.printStackTrace()
            }
            // on below line we are displaying a toast message as audio player.
            Toast.makeText(applicationContext, "Audio started playing..", Toast.LENGTH_SHORT).show()

        }

        pauseIB.setOnClickListener {

            pauseIB.visibility = Button.INVISIBLE
            playIB.visibility = Button.VISIBLE
            // on below line we are checking
            // if media player is playing.
            if (mediaPlayer.isPlaying) {
                // if media player is playing we
                // are stopping it on below line.
                mediaPlayer.stop()

                // on below line we are resetting
                // our media player.
                mediaPlayer.reset()

                // on below line we are calling
                // release to release our media player.
                mediaPlayer.release()

                // on below line we are displaying a toast message to pause audio/
                Toast.makeText(applicationContext, "Audio has been  paused..", Toast.LENGTH_SHORT)
                    .show()

            } else {
                // if audio player is not displaying we are displaying below toast message
                Toast.makeText(applicationContext, "Audio not played..", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun updatePage() {
        val pageData = pages[currentPage]
        imageView.setImageResource(pageData.imageResId)
        pageNumberTextView.text = pageData.pageNumber
        textView.text = pageData.text
    }

}

data class PageData(val imageResId: Int, val pageNumber: String, val text: String)

