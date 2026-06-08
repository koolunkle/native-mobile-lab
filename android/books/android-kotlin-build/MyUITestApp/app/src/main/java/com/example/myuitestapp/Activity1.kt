package com.example.myuitestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myuitestapp.databinding.Activity1Binding

class Activity1 : AppCompatActivity() {

    private lateinit var binding: Activity1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setupEvents()
    }

    private fun setupEvents() {
        binding.activity1Button.setOnClickListener {
            (application as MyApplication).synchronizer.executeAfterDelay { seconds ->
                startActivity(Activity2.newIntent(this, seconds))
            }
        }
    }
}