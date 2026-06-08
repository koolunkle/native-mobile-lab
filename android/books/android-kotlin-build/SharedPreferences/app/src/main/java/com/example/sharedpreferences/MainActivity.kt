package com.example.sharedpreferences

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val preferenceViewModel: PreferenceViewModel by viewModels {
        PreferenceViewModel.Factory
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

        // val preferenceWrapper = (application as PreferenceApplication).preferenceWrapper

        /*val preferenceViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(PreferenceViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return PreferenceViewModel(preferenceWrapper) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        })[PreferenceViewModel::class.java]*/

        setValues()
        setupEvents()
    }

    private fun setValues() {
        preferenceViewModel.getText().observe(this) {
            binding.activityMainTextView.text = it
        }
    }

    private fun setupEvents() {
        binding.activityMainButton.setOnClickListener {
            preferenceViewModel.saveText(binding.activityMainEditText.text.toString())
        }
    }

    /*private fun setValues() {
        val prefs = getSharedPreferences("my-prefs-file", MODE_PRIVATE)

        *//*val editor = prefs.edit()

        editor.putBoolean("my_key_1", true)
        editor.putString("my_key_2", "my string")
        editor.putLong("my_key_3", 1L)

        editor.apply()*//*

        prefs.edit { // prefs.edit(commit = true)
            putBoolean("my_key_1", true)
            putString("my_key_2", "my string")
            putLong("my_key_3", 1L)
        }
    }*/
}