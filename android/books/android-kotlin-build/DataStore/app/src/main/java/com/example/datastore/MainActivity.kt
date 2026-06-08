package com.example.datastore

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.datastore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val preferenceViewModel: SettingsViewModel by viewModels {
        SettingsViewModel.Factory
    }

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
        setupEvents()
    }

    private fun setValues() {
        preferenceViewModel.textLiveData.observe(this) {
            binding.activityMainTextView.text = it
        }
    }

    private fun setupEvents() {
        binding.activityMainButton.setOnClickListener {
            preferenceViewModel.saveText(binding.activityMainEditText.text.toString())
        }
    }
}