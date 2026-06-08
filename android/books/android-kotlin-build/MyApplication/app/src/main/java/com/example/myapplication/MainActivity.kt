package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // val webView = WebView(this).apply {
        //    settings.javaScriptEnabled = true
        //}

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        // setContentView(webView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // webView.loadUrl("https://www.google.com")

        setupEvents()
    }

    private fun setupEvents() {
        findViewById<Button>(R.id.enter_button)?.setOnClickListener {
            val greetingDisplay = findViewById<TextView>(R.id.greeting_display)
            val firstName = findViewById<TextInputEditText>(R.id.first_name)?.text.toString().trim()
            val lastName = findViewById<TextInputEditText>(R.id.last_name)?.text.toString().trim()

            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                val fullName = "$firstName $lastName"
                greetingDisplay?.text = getString(R.string.welcome_to_the_app, fullName)
            } else {
                Toast.makeText(
                    this, resources.getString(R.string.please_enter_a_name), Toast.LENGTH_SHORT
                ).apply {
                    setGravity(Gravity.CENTER, 0, 0)
                    show()
                }
            }
        }
    }
}