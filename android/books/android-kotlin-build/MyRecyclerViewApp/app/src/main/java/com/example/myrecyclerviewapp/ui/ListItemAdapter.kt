package com.example.myrecyclerviewapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myrecyclerviewapp.ImageLoader
import com.example.myrecyclerviewapp.databinding.ItemCatBinding
import com.example.myrecyclerviewapp.databinding.ItemTitleBinding
import com.example.myrecyclerviewapp.model.CatUiModel
import com.example.myrecyclerviewapp.model.ListItemUiModel

private const val VIEW_TYPE_TITLE = 0
private const val VIEW_TYPE_CAT = 1

class ListItemAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    // private val onClickListener: OnClickListener,
    private val onClickListener: (CatUiModel) -> Unit,
) : ListAdapter<ListItemUiModel, ListItemViewHolder>(CatDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder =
        when (viewType) {
            VIEW_TYPE_TITLE -> {
                val binding = ItemTitleBinding.inflate(layoutInflater, parent, false)
                TitleViewHolder(binding)
            }

            VIEW_TYPE_CAT -> {
                val binding = ItemCatBinding.inflate(layoutInflater, parent, false)
                CatViewHolder(binding, imageLoader) { cat ->
                    onClickListener(cat)
                }
            }

            else -> throw IllegalArgumentException("Unknown view type requested: $viewType")
        }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is TitleViewHolder -> {
                val titleItem = item as ListItemUiModel.Title
                holder.bindData(titleItem.title)
            }

            is CatViewHolder -> {
                val catItem = item as ListItemUiModel.Cat
                holder.bindData(catItem.cat)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ListItemUiModel.Title -> VIEW_TYPE_TITLE
        is ListItemUiModel.Cat -> VIEW_TYPE_CAT
    }

    /*interface OnClickListener {
        fun onItemClick(cat: CatUiModel)
    }*/

    companion object {
        private val CatDiffCallback = object : DiffUtil.ItemCallback<ListItemUiModel>() {
            override fun areItemsTheSame(
                oldItem: ListItemUiModel, newItem: ListItemUiModel
            ): Boolean {
                return when (oldItem) {
                    is ListItemUiModel.Title if newItem is ListItemUiModel.Title -> {
                        oldItem.title == newItem.title
                    }

                    is ListItemUiModel.Cat if newItem is ListItemUiModel.Cat -> {
                        oldItem.cat.id == newItem.cat.id
                    }

                    else -> false
                }
            }

            override fun areContentsTheSame(
                oldItem: ListItemUiModel, newItem: ListItemUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}