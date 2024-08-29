package com.example.bookid

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.widget.ImageButton

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val charbtn1 = findViewById<ImageButton>(R.id.character1)
        charbtn1.setOnClickListener {
            startActivity(Intent(this, CharacterView::class.java))
            finish();
        }
    }
}






