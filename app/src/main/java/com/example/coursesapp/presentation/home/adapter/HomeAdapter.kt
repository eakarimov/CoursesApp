package com.example.coursesapp.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.coursesapp.presentation.home.model.HomeUiItem
import com.example.coursesapp.presentation.home.adapter.delegate.courseDelegate
import com.example.coursesapp.presentation.home.adapter.delegate.searchDelegate
import com.example.coursesapp.presentation.home.adapter.delegate.sortDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class HomeAdapter(
    onSortClick: () -> Unit
) : AsyncListDifferDelegationAdapter<HomeUiItem>(diffUtil) {

    init {
        delegatesManager.apply {
            addDelegate(searchDelegate())
            addDelegate(sortDelegate(onSortClick))
            addDelegate(courseDelegate())
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<HomeUiItem>() {

            override fun areItemsTheSame(oldItem: HomeUiItem, newItem: HomeUiItem): Boolean {
                return when {
                    oldItem is HomeUiItem.Course && newItem is HomeUiItem.Course ->
                        oldItem.id == newItem.id

                    oldItem::class == newItem::class ->
                        true

                    else -> false
                }
            }

            override fun areContentsTheSame(oldItem: HomeUiItem, newItem: HomeUiItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}