package com.example.myuitestapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myuitestapp.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {

    private lateinit var binding: Activity2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setValues()
    }

    private fun setValues() {
        binding.activity2TextView.text =
            getString(R.string.opened_after_x_seconds, intent.getIntExtra(EXTRA_SECONDS, 0))
    }

    companion object {
        private const val EXTRA_SECONDS = "extra_seconds"

        fun newIntent(context: Context, seconds: Int): Intent =
            Intent(context, Activity2::class.java).putExtra(EXTRA_SECONDS, seconds)
    }
}
