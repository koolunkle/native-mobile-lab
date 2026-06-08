package com.example.tabnavigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tabnavigation.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
    }

    private fun setValues() {
        val viewpager = binding.viewPager
        val tabLayout = binding.tabLayout
        val adapter = MovieGenresAdapter(supportFragmentManager, lifecycle)

        viewpager.adapter = adapter

        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = resources.getString(TAB_GENRES_SCROLLABLE[position])
        }.attach()
    }
}