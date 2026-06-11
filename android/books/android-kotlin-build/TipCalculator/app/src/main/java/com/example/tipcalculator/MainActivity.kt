package com.example.tipcalculator

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.computeButton.setOnClickListener {
            val amount = binding.amountText.text.toString().ifBlank { "0" }
            val percent = binding.percentText.text.toString().ifBlank { "0" }
            val intent = Intent(this, OutputActivity::class.java).apply {
                putExtra("amount", amount)
                putExtra("percent", percent)
            }
            // startActivity(intent)
            // startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this, binding.image, "transition_name")
                    .toBundle()
            )
        }
    }
}
