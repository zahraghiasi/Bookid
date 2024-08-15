package com.example.bookid

import android.os.Bundle
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Session
import com.google.ar.core.exceptions.UnavailableException as ARException

class ARCameraActivity : AppCompatActivity() {
    private var arSession: Session? = null
    private lateinit var surfaceView: SurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcamera)

        surfaceView = findViewById(R.id.surfaceview)

        try {
            when (ArCoreApk.getInstance().requestInstall(this, true)) {
                ArCoreApk.InstallStatus.INSTALLED -> arSession = Session(this)
                ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                    // Install requested, nothing to do here
                }
            }
        } catch (e: ARException) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        arSession?.let {
            try {
                it.resume()
                // TODO: Configure your SurfaceView here to work with AR Session
                // For example, setting up AR session with the SurfaceView
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        arSession?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        arSession?.close()
    }
}
