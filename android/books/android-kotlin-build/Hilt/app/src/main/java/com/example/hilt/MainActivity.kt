package com.example.hilt

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setValues()
        setupEvents()
    }

    private fun setValues() {
        mainViewModel.numberLiveData.observe(this) {
            binding.activityMainTextView.text = it.toString()
        }
    }

    private fun setupEvents() {
        binding.activityMainButton.setOnClickListener {
            mainViewModel.generateNextNumber()
        }
    }
}