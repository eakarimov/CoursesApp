package com.example.coursesapp.presentation.home.adapter.delegate

import com.example.coursesapp.databinding.ItemSortBinding
import com.example.coursesapp.presentation.home.model.HomeUiItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun sortDelegate(onClick: () -> Unit) = adapterDelegateViewBinding<HomeUiItem.Sort, HomeUiItem, ItemSortBinding>(
    { layoutInflater, root -> ItemSortBinding.inflate(layoutInflater, root, false)}
) {

    binding.btnSort.setOnClickListener {
        onClick()
    }
}