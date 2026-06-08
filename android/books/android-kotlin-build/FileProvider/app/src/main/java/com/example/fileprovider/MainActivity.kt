package com.example.fileprovider

import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fileprovider.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val assetFileManager: AssetFileManager by lazy {
        AssetFileManager(applicationContext.assets)
    }
    private val providerFileManager: ProviderFileManager by lazy {
        ProviderFileManager(
            applicationContext,
            FileToUriMapper(),
            Executors.newSingleThreadExecutor()
        )
    }

    private val createDocumentResult =
        registerForActivityResult(ActivityResultContracts.CreateDocument("text/plain")) { uri ->
            uri?.let {
                val newFileName = "Copied.txt"
                providerFileManager.writeStreamFromUri(
                    newFileName,
                    assetFileManager.getMyAppFileInputStream(),
                    uri
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupEvents()
    }

    private fun setupEvents() {
        binding.activityMainFileProvider.setOnClickListener {
            Log.d(TAG, "onClick: Provider")
            val newFileName = "Copied.txt"
            providerFileManager.writeStream(newFileName, assetFileManager.getMyAppFileInputStream())
        }

        binding.activityMainSaf.setOnClickListener {
            Log.d(TAG, "onClick: SAF")
            createDocumentResult.launch("Copied.txt")
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}