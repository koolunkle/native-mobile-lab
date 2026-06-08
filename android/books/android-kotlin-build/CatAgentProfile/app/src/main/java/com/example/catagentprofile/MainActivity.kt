package com.example.catagentprofile

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.catagentprofile.api.TheCatApiService
import com.example.catagentprofile.databinding.ActivityMainBinding
import com.example.catagentprofile.model.ImageResultData
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val moshi by lazy {
        Moshi.Builder()
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val theCatApiService by lazy {
        retrofit.create(TheCatApiService::class.java)
    }

    private val imageLoader: ImageLoader by lazy {
        GlideImageLoader(this)
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

        getCatImageResponse()
    }

    private fun getCatImageResponse() {
        val call = theCatApiService.searchImages(1, "full")

        call.enqueue(object : Callback<List<ImageResultData>> {
            override fun onResponse(
                call: Call<List<ImageResultData>>, response: Response<List<ImageResultData>>
            ) {
                if (response.isSuccessful) {
                    val imageResults = response.body()
                    val firstImageUrl = imageResults?.firstOrNull()?.imageUrl.orEmpty()

                    if (!firstImageUrl.isBlank()) {
                        imageLoader.loadImage(firstImageUrl, binding.mainProfileImage)
                    } else {
                        Log.d(TAG, "Missing image URL")
                    }

                    binding.mainAgentBreedValue.text =
                        imageResults?.firstOrNull()?.breeds?.firstOrNull()?.name ?: "Unknown"
                } else {
                    Log.e(
                        TAG,
                        "Failed to get search results\n${response.errorBody()?.string().orEmpty()}"
                    )
                }
            }

            override fun onFailure(call: Call<List<ImageResultData>>, t: Throwable) {
                Log.e(TAG, "Failed to get search results", t)
            }

        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}