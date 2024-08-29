package com.example.bookid

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.google.android.play.core.install.model.InstallStatus
import com.google.ar.core.ArCoreApk
import com.google.ar.core.AugmentedImage
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.core.TrackingState
import com.google.ar.core.exceptions.CameraNotAvailableException
import com.google.ar.core.exceptions.UnavailableException
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import android.os.Build
import androidx.core.app.ActivityCompat

class ARCameraActivity : AppCompatActivity() {
    private lateinit var cameraPermissionLauncher: ActivityResultLauncher<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.mediaPlaybackRequiresUserGesture = false

        // Set the WebChromeClient to handle permissions
        myWebView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                val resources = request.resources
                for (resource in resources) {
                    if (resource == PermissionRequest.RESOURCE_VIDEO_CAPTURE) {
                        request.grant(arrayOf(PermissionRequest.RESOURCE_VIDEO_CAPTURE))
                        return
                    }
                }
                request.deny() // Deny other permissions if not necessary
            }
        }

        // Set the WebViewClient to handle page loading
        myWebView.webViewClient = WebViewClient()

        // Load the URL
        myWebView.loadUrl("https://ghiac.ir/A/")

        // Set up the camera permission launcher
        cameraPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                println("CAMERA PERMISSION GRANTED")
            } else {
                println("CAMERA PERMISSION NOT GRANTED")
            }
        }

        // Check and request camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

