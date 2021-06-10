package com.sosnowskydevelop.saratovregiontouristroutes.activities

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sosnowskydevelop.saratovregiontouristroutes.R

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val imageView: ImageView = findViewById(R.id.image_view)
        val extras = intent.extras

        if (extras != null) {
            val title = extras.getString("title")
            supportActionBar?.title = title

            val imageId = extras.getInt("image_id")
            imageView.setImageResource(imageId)
        }
    }
}