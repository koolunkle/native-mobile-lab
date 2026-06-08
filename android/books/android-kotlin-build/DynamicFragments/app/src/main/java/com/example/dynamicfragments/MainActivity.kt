package com.example.dynamicfragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dynamicfragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), StarSignListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setValues(savedInstanceState)
    }

    private fun setValues(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val listFragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainer.id, listFragment)
                .commit()
        }
    }

    override fun onSelected(id: Int) {
        val detailFragment = DetailFragment.newInstance(id)

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val STAR_SIGN_ID = "STAR_SIGN_ID"
    }
}

interface StarSignListener {
    fun onSelected(id: Int)
}