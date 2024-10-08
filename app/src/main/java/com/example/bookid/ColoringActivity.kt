package com.example.bookid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class ColoringActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coloring)

        val backButton = findViewById<ImageButton>(R.id.back_btn)
        backButton.setOnClickListener {
//            Toast.makeText(applicationContext,"You clicked Submit Button.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, GameActivity::class.java))
            finish();
        }

    }
}