package com.example.matchmakeover

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GetStartedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        // Find views
        val logo: ImageView = findViewById(R.id.logo)
        val matchMakeoverText: TextView = findViewById(R.id.MatchMakeover)

        // Optionally set dynamic content for the views
        matchMakeoverText.text = getString(R.string.match_makeover)

        // Navigate to the next activity after a delay
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AdminLogin::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3-second delay
    }
}
