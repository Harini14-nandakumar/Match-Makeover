package com.example.matchmakeover

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ImageUpload : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_upload)

        val buttonTakePhoto: ImageButton = findViewById(R.id.buttonTakePhoto)
        val buttonAlbums: ImageButton = findViewById(R.id.buttonAlbums)

        buttonTakePhoto.setOnClickListener {
            Toast.makeText(this, "Take Photo clicked", Toast.LENGTH_SHORT).show()
            val takePhotoIntent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(takePhotoIntent)
        }

        buttonAlbums.setOnClickListener {
            Toast.makeText(this, "Open Albums clicked", Toast.LENGTH_SHORT).show()
            val openAlbumsIntent = Intent(Intent.ACTION_PICK)
            openAlbumsIntent.type = "image/*"
            startActivity(openAlbumsIntent)
        }
    }
}
