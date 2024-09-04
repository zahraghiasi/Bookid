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

class PodcastActivity : ComponentActivity() {

    private lateinit var imageView: ImageView
    private lateinit var pageNumberTextView: TextView
    private lateinit var textView: TextView
    private lateinit var nextButton: ImageButton
    private lateinit var preButton: ImageButton

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
    }

    private fun updatePage() {
        val pageData = pages[currentPage]
        imageView.setImageResource(pageData.imageResId)
        pageNumberTextView.text = pageData.pageNumber
        textView.text = pageData.text
    }
}

data class PageData(val imageResId: Int, val pageNumber: String, val text: String)

