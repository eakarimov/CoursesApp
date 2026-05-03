package com.example.coursesapp.presentation.favorite.adapter

import com.example.coursesapp.R
import com.example.coursesapp.databinding.ItemCourseBinding
import com.example.coursesapp.presentation.favorite.model.FavoriteUiItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun courseDelegate() =
    adapterDelegateViewBinding<FavoriteUiItem.Course, FavoriteUiItem, ItemCourseBinding>(
        { layoutInflater, root -> ItemCourseBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            with(binding) {
                tvTitle.text = item.title
                tvPrice.text = getString(R.string.course_price, item.price)
                tvDescription.text = item.text
                tvRating.text = item.rate
                tvStartDate.text = item.startDate
                btnFavorite.isSelected = item.hasLike
            }
        }
    }