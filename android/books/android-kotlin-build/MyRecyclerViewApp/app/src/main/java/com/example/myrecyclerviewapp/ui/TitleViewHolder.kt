package com.example.myrecyclerviewapp.ui

import com.example.myrecyclerviewapp.databinding.ItemTitleBinding

class TitleViewHolder(
    private val binding: ItemTitleBinding
) : ListItemViewHolder(binding.root) {

    fun bindData(title: String) {
        // require(listItem is ListItemUiModel.Title) { "Expected ListItemUiModel.Title" }
        binding.itemTitleTitle.text = title
    }
}