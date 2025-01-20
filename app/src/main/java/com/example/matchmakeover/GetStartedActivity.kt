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
            // Example: Decide where to navigate based on a condition
            val isAdmin = checkUserType() // Replace with your logic
            val intent = if (isAdmin) {
                Intent(this, AdminLogin::class.java) // Navigate to AdminLogin
            } else {
                Intent(this, UserLogin::class.java) // Navigate to UserLogin
            }
            startActivity(intent)
            finish() // Finish the current activity so it doesn't remain in the back stack
        }, 3000) // 3-second delay
    }

    // Example function to determine the user type
    private fun checkUserType(): Boolean {
        // Replace this with your actual logic to determine the user type
        // For example, check a flag in SharedPreferences or pass data from a previous screen
        return false // Return true for admin, false for user
    }
}
