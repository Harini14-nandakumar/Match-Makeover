package com.example.matchmakeover

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.ChatRequest
import com.example.matchmakeover.response.ChatResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Chatbox : AppCompatActivity() {

    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var messagesLayout: LinearLayout
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatbox)

        // Initialize UI components
        messageInput = findViewById(R.id.etMessage)
        sendButton = findViewById(R.id.btnSend)
        messagesLayout = findViewById(R.id.messagesLayout)
        scrollView = findViewById(R.id.chatScrollView)

        // Send button click listener
        sendButton.setOnClickListener {
            val userMessage = messageInput.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                addMessage(userMessage, "user")
                sendMessageToAPI(userMessage)
                messageInput.text.clear()
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addMessage(message: String, sender: String) {
        val messageTextView = TextView(this).apply {
            text = message
            setPadding(16, 8, 16, 8)
            setBackgroundColor(
                ContextCompat.getColor(
                    this@Chatbox,
                    if (sender == "user") android.R.color.holo_blue_light else android.R.color.holo_purple
                )
            )
            setTextColor(ContextCompat.getColor(this@Chatbox, android.R.color.black))
            textSize = 16f

            // Add margins
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(8, 8, 8, 8)
            }
            layoutParams = params
        }

        messagesLayout.addView(messageTextView)

        // Scroll to the latest message
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun sendMessageToAPI(userMessage: String) {
        val request = ChatRequest(username = "User", message = userMessage) // Change to dynamic username if needed

        val apiService =  RetrofitClient.retrofitInstance.create(ApiInterface::class.java)
        apiService.sendMessage(request).enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        addMessage(it.botResponse, "bot")
                    } ?: addMessage("No response received", "bot")
                } else {
                    addMessage("Server error: ${response.code()}", "bot")
                }
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                addMessage("Failed to send message: ${t.localizedMessage}", "bot")
            }
        })
    }
}
