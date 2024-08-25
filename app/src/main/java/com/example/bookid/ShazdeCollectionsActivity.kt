package com.example.bookid

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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

class ShazdeCollectionsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sahzdeh_collections)

        val backButton = findViewById<ImageButton>(R.id.back_btn)
        backButton.setOnClickListener {
            startActivity(Intent(this, CharacterView::class.java))
            finish();
        }

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog_box2)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val btnShowDialog = findViewById<ImageButton>(R.id.bookBox1)
        val closebtn: ImageButton = dialog.findViewById(R.id.close_button)
        val playbtn: ImageButton = dialog.findViewById(R.id.play_button)
        
        btnShowDialog.setOnClickListener {
            dialog.show()

            playbtn.setOnClickListener(){
                dialog.dismiss();
                startActivity(Intent(this, MediaSelectionActivity::class.java))
                finish();
            }

            closebtn.setOnClickListener(){
                dialog.dismiss();
            }
        }

    }
}