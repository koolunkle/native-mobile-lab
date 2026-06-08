package com.example.dependencyinjection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.dependencyinjection.container.MainContainer
import com.example.dependencyinjection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainContainer: MainContainer by lazy {
        MainContainer((application as RandomApplication).applicationContainer.numberRepository)
    }

    private lateinit var viewModel: MainViewModel

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

        viewModel = ViewModelProvider(
            this,
            mainContainer.getMainViewModelFactory()
        )[MainViewModel::class.java]

        setValues()
        setupEvents()
    }

    private fun setValues() {
        viewModel.numberLiveData.observe(this) {
            binding.activityMainTextView.text = it.toString()
        }
    }

    private fun setupEvents() {
        binding.activityMainButton.setOnClickListener {
            viewModel.generateNextNumber()
        }
    }
}