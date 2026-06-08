package com.example.dagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dagger.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as RandomApplication).applicationComponent.createMainSubcomponent()
            .inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.numberLiveData.observe(this) {
            binding.activityMainTextView.text = it.toString()
        }

        binding.activityMainButton.setOnClickListener {
            viewModel.generateNextNumber()
        }
    }
}