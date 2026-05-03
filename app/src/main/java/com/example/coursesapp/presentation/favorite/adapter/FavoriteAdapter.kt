package com.example.coursesapp.presentation.favorite.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.coursesapp.presentation.favorite.model.FavoriteUiItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FavoriteAdapter : AsyncListDifferDelegationAdapter<FavoriteUiItem>(diffUtil) {

    init {
        delegatesManager.apply {
            addDelegate(titleDelegate())
            addDelegate(courseDelegate())
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<FavoriteUiItem>() {

            override fun areItemsTheSame(
                oldItem: FavoriteUiItem,
                newItem: FavoriteUiItem
            ): Boolean {
                return when {
                    oldItem is FavoriteUiItem.Course && newItem is FavoriteUiItem.Course ->
                        oldItem.id == newItem.id

                    oldItem::class == newItem::class ->
                        true

                    else -> false
                }
            }

            override fun areContentsTheSame(
                oldItem: FavoriteUiItem,
                newItem: FavoriteUiItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}