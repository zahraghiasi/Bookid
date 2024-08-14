package com.example.bookid

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookid.ui.theme.BookidTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val charbtn1 = findViewById<ImageButton>(R.id.character1)
        charbtn1.setOnClickListener {
            Toast.makeText(applicationContext,"You clicked Submit Button.",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, CharacterView::class.java))
            finish();
        }
    }
}

