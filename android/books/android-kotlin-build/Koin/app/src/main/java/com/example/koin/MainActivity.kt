package com.example.koin

import android.os.Bundle
import com.example.koin.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity

class MainActivity : ScopeActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by inject()

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

//class MainActivity : ScopeActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//
//    val classB: MyApplication.ClassB by inject()
//    // val viewModel: MyApplication.MyViewModel by viewModel()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        val classB: MyApplication.ClassB = get()
//        val viewModel: MyViewModel = getViewModel()
//    }
//}