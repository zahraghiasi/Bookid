package com.example.bookid

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val backButton = findViewById<ImageButton>(R.id.back_btn)
        backButton.setOnClickListener {
            startActivity(Intent(this, MediaSelectionActivity::class.java))
            finish();
        }

        val coloringButton = findViewById<ImageButton>(R.id.coloring)
        coloringButton.setOnClickListener {
            startActivity(Intent(this, ColoringActivity::class.java))
            finish();
        }
        val puzzleButton = findViewById<ImageButton>(R.id.puzzle)
        puzzleButton.setOnClickListener {
            startActivity(Intent(this, PuzzleActivity::class.java))
            finish();
        }

        val jumpButton = findViewById<ImageButton>(R.id.jump)
        jumpButton.setOnClickListener {
            startActivity(Intent(this, JumpActivity::class.java))
            finish();
        }
    }
}