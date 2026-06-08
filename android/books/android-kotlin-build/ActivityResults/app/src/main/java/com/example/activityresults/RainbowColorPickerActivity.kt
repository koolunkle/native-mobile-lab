package com.example.activityresults

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activityresults.databinding.ActivityRainbowColorPickerBinding

class RainbowColorPickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRainbowColorPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRainbowColorPickerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupEvents()
    }

    private fun setRainbowColor(colorName: String, color: Int) {
        Intent().let { pickedColorIntent ->
            pickedColorIntent.putExtra(RAINBOW_COLOR_NAME, colorName)
            pickedColorIntent.putExtra(RAINBOW_COLOR, color)
            setResult(RESULT_OK, pickedColorIntent)
            finish()
        }
    }

    private fun setupEvents() {
        val colorPickerClickListener = View.OnClickListener { view ->
            when (view) {
                binding.redButton -> {
                    setRainbowColor(getString(R.string.red), R.color.red)
                }

                binding.orangeButton -> {
                    setRainbowColor(getString(R.string.orange), R.color.orange)
                }

                binding.yellowButton -> {
                    setRainbowColor(getString(R.string.yellow), R.color.yellow)
                }

                binding.greenButton -> {
                    setRainbowColor(getString(R.string.green), R.color.green)
                }

                binding.blueButton -> {
                    setRainbowColor(getString(R.string.blue), R.color.blue)
                }

                binding.indigoButton -> {
                    setRainbowColor(getString(R.string.indigo), R.color.indigo)
                }

                binding.violetButton -> {
                    setRainbowColor(getString(R.string.violet), R.color.violet)
                }

                else -> {
                    Toast.makeText(this, getString(R.string.unexpected_color), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        binding.redButton.setOnClickListener(colorPickerClickListener)
        binding.orangeButton.setOnClickListener(colorPickerClickListener)
        binding.yellowButton.setOnClickListener(colorPickerClickListener)
        binding.greenButton.setOnClickListener(colorPickerClickListener)
        binding.blueButton.setOnClickListener(colorPickerClickListener)
        binding.indigoButton.setOnClickListener(colorPickerClickListener)
        binding.violetButton.setOnClickListener(colorPickerClickListener)
    }
}