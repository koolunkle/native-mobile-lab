package com.example.mytestapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mytestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var textFormatter: TextFormatter
    private lateinit var textFormatter2: TextFormatter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setValues()
        // setupEvents()
        setupEvents2()
    }

    private fun setValues() {
        textFormatter = TextFormatter(FactorialGenerator(), applicationContext)
        textFormatter2 = TextFormatter2(NumberAdder(), applicationContext)
    }

    private fun setupEvents() {
        binding.button.setOnClickListener {
            binding.textView.text =
                textFormatter.getFactorialResult(binding.editText.text.toString().toInt())
        }
    }

    private fun setupEvents2() {
        binding.button.setOnClickListener {
            val input = binding.editText.text.toString().toIntOrNull() ?: 0
            textFormatter2.getSumResult(input) {
                binding.textView.text = it
            }
        }
    }
}