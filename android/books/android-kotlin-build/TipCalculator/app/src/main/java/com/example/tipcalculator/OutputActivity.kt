package com.example.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipcalculator.databinding.ActivityOutputBinding
import java.math.BigDecimal

class OutputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOutputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val amount = intent?.getStringExtra("amount")?.toBigDecimal() ?: BigDecimal.ZERO
        val percent = intent?.getStringExtra("percent")?.toBigDecimal() ?: BigDecimal.ZERO
        val tip = amount * (percent.divide("100".toBigDecimal()))

        binding.tipText.text = getString(R.string.tip, tip)
    }
}
