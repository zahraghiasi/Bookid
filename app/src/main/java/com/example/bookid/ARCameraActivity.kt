package com.example.bookid

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
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

class ARCameraActivity : AppCompatActivity() {

    private var session: Session? = null
    private var dogIndex: Int? = null
    private var catIndex: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verify ARCore support
        if (isARCoreSupportedAndUpToDate()) {
            createSession()

            // Load the image database after creating the session
            val imageDatabase = this.assets.open("imgdb.imgdb").use {
                AugmentedImageDatabase.deserialize(session, it)
            }

            // Configure the session with the image database
            val config = Config(session)
            config.augmentedImageDatabase = imageDatabase
            session?.configure(config)
        }
    }

    fun onDrawFrame() {
        // Get the current frame from the AR session
        val frame = session?.update()

        // Get updated augmented images
        val updatedAugmentedImages = frame?.getUpdatedTrackables(AugmentedImage::class.java)

        for (img in updatedAugmentedImages ?: emptyList()) {
            if (img.trackingState == TrackingState.TRACKING) {
                // Determine the tracking method
                when (img.trackingMethod) {
                    AugmentedImage.TrackingMethod.LAST_KNOWN_POSE -> {
                        // The planar target is currently being tracked based on its last known pose.
                    }
                    AugmentedImage.TrackingMethod.FULL_TRACKING -> {
                        // The planar target is being tracked using the current camera image.
                    }
                    AugmentedImage.TrackingMethod.NOT_TRACKING -> {
                        // The planar target isn't been tracked.
                    }
                }

                // Check which image this is and render accordingly
                when (img.name) {
                    "dogImageName" -> {
                        // TODO: Render a 3D version of a dog at img.centerPose
                    }
                    "catImageName" -> {
                        // TODO: Render a 3D version of a cat at img.centerPose
                    }
                }
            }
        }
    }

    @OptIn(UnstableApi::class)
    private fun isARCoreSupportedAndUpToDate(): Boolean {
        return when (ArCoreApk.getInstance().checkAvailability(this)) {
            ArCoreApk.Availability.SUPPORTED_INSTALLED -> true
            ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD, ArCoreApk.Availability.SUPPORTED_NOT_INSTALLED -> {
                try {
                    when (ArCoreApk.getInstance().requestInstall(this, true)) {
                        ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                            Log.i(TAG, "ARCore installation requested.")
                            false
                        }
                        ArCoreApk.InstallStatus.INSTALLED -> true
                    }
                } catch (e: UnavailableException) {
                    Log.e(TAG, "ARCore not installed", e)
                    false
                }
            }
            ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE -> false
            else -> false
        }
    }

    private fun createSession() {
        session = Session(this)
        val config = Config(session)
        session?.configure(config)
    }

    @OptIn(UnstableApi::class)
    override fun onResume() {
        super.onResume()
        try {
            session?.resume()
        } catch (e: CameraNotAvailableException) {
            Log.e(TAG, "Camera not available. Try restarting the app.", e)
            session = null
        }
    }

    override fun onPause() {
        super.onPause()
        session?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        session?.close()
        session = null
    }

    companion object {
        private const val TAG = "ARCameraActivity"
    }
}

