package com.example.coursesapp.presentation.home.adapter.delegate

import com.example.coursesapp.databinding.ItemSearchBinding
import com.example.coursesapp.presentation.home.model.HomeUiItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun searchDelegate() = adapterDelegateViewBinding<HomeUiItem.Search, HomeUiItem, ItemSearchBinding>(
    { layoutInflater, root -> ItemSearchBinding.inflate(layoutInflater, root, false) }
) {
}