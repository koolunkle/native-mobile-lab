package com.example.dualpanelayouts

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dualpanelayouts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), StarSignListener {

    private lateinit var binding: ActivityMainBinding

    private var isDualPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setValues()
    }

    override fun onSelected(id: Int) {
        if (isDualPane) {
            val detailFragment =
                supportFragmentManager.findFragmentById(R.id.star_sign_detail) as DetailFragment
            detailFragment.setStarSignData(id)
        } else {
            val detailIntent = Intent(this, DetailActivity::class.java).apply {
                putExtra(STAR_SIGN_ID, id)
            }
            startActivity(detailIntent)
        }
    }

    private fun setValues() {
        isDualPane = binding.starSignDetail != null
    }

    companion object {
        const val STAR_SIGN_ID = "STAR_SIGN_ID"
    }
}

interface StarSignListener {
    fun onSelected(id: Int)
}