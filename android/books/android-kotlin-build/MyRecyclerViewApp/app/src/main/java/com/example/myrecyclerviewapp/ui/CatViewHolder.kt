package com.example.myrecyclerviewapp.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.ImageLoader
import com.example.myrecyclerviewapp.databinding.ItemCatBinding
import com.example.myrecyclerviewapp.model.CatBreed
import com.example.myrecyclerviewapp.model.CatUiModel
import com.example.myrecyclerviewapp.model.Gender

private const val FEMALE_SYMBOL = "\u2640"
private const val MALE_SYMBOL = "\u2642"
private const val UNKNOWN_SYMBOL = "?"

class CatViewHolder(
    // containerView: View,
    private val binding: ItemCatBinding,
    private val imageLoader: ImageLoader,
    // private val onClickListener: OnClickListener
    private val onClickListener: (CatUiModel) -> Unit
) : ListItemViewHolder(binding.root) {

    private var currentCat: CatUiModel? = null

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition

            if (position != RecyclerView.NO_POSITION) {
                currentCat?.let { onClickListener(it) }
            }
        }
    }

    fun bindData(cat: CatUiModel) {
        // require(listItem is ListItemUiModel.Cat) { "Expected ListItemUiModel.Cata" }
        // val cat = listItem.cat

        this.currentCat = cat

        imageLoader.loadImage(cat.imageUrl, binding.itemCatPhoto)

        binding.itemCatName.text = cat.name
        binding.itemCatBreed.text = when (cat.breed) {
            CatBreed.AmericanCurl -> "American Curl"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.ExoticShorthair -> "Exotic Shorthair"
        }
        binding.itemCatBiography.text = cat.biography
        binding.itemCatGender.text = when (cat.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            Gender.Unknown -> UNKNOWN_SYMBOL
        }
    }

    /*interface OnClickListener {
        fun onClick(cat: CatUiModel)
    }*/
}

