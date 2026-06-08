package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityRgbBinding

class RGBActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRgbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRgbBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupEvents()
    }

    private fun setupEvents() {
        binding.createRgbColorButton.setOnClickListener {
            val redChannel = binding.redChannelEditText.text.toString()
            val greenChannel = binding.greenChannelEditText.text.toString()
            val blueChannel = binding.blueChannelEditText.text.toString()

            if (redChannel.length == 2 && greenChannel.length == 2 && blueChannel.length == 2) {
                /*val r = redChannel.toInt(16)
                val g = greenChannel.toInt(16)
                val b = blueChannel.toInt(16)*/

                val rgbColor = "#$redChannel$greenChannel$blueChannel"
                val rgbColorInt = Color.parseColor(rgbColor)

                binding.createdColorDisplayTextView.setTextColor(rgbColorInt)
            } else {
                Toast.makeText(this, getString(R.string.please_enter_a_name), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}