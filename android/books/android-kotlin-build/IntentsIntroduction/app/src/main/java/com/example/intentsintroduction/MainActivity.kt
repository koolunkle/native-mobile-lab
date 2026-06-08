package com.example.intentsintroduction

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intentsintroduction.databinding.ActivityMainBinding

const val FULL_NAME_KEY = "FULL_NAME_KEY"

class MainActivity : AppCompatActivity() {

    private lateinit var binidng: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binidng = ActivityMainBinding.inflate(layoutInflater)
        val view = binidng.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupEvents()
    }

    private fun setupEvents() {
        binidng.submitButton.setOnClickListener {
            val fullName = binidng.fullName.text.toString()

            if (fullName.isNotEmpty()) {
                val welcomeIntent = Intent(this, WelcomeActivity::class.java).apply {
                    putExtra(FULL_NAME_KEY, fullName)
                }
                startActivity(welcomeIntent)
            } else {
                Toast.makeText(this, getString(R.string.full_name_label), Toast.LENGTH_SHORT).show()
            }
        }
    }
}