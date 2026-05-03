package com.example.coursesapp.presentation.favorite.adapter

import com.example.coursesapp.R
import com.example.coursesapp.databinding.ItemTitleBinding
import com.example.coursesapp.presentation.favorite.model.FavoriteUiItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun titleDelegate() =
    adapterDelegateViewBinding<FavoriteUiItem.Title, FavoriteUiItem, ItemTitleBinding>(
        { layoutInflater, root -> ItemTitleBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.tvTitle.text = getString(R.string.favorite_title)
        }
    }