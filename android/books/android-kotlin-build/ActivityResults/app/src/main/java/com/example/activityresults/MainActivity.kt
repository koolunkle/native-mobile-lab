package com.example.activityresults

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.activityresults.databinding.ActivityMainBinding

const val RAINBOW_COLOR_NAME = "RAINBOW_COLOR_NAME"
const val RAINBOW_COLOR = "RAINBOW_COLOR"
const val DEFAULT_COLOR = "#FFFFFF"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val data = activityResult.data
            val backgroundColor = data?.getIntExtra(RAINBOW_COLOR, DEFAULT_COLOR.toColorInt())
                ?: DEFAULT_COLOR.toColorInt()
            val colorName = data?.getStringExtra(RAINBOW_COLOR_NAME) ?: ""
            val colorMessage = getString(R.string.color_chosen_message, colorName)

            binding.rainbowColor.run {
                setBackgroundColor(ContextCompat.getColor(this@MainActivity, backgroundColor))
                text = colorMessage
                isVisible = true
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
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
        binding.submitButton.setOnClickListener {
            val intent = Intent(this, RainbowColorPickerActivity::class.java)
            startForResult.launch(intent)
        }
    }
}