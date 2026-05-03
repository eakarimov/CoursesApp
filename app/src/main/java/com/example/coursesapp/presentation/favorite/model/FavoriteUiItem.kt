package com.example.coursesapp.presentation.favorite.model

sealed class FavoriteUiItem {

    object Title: FavoriteUiItem()

    data class Course(
        val id: Int,
        val title: String,
        val text: String,
        val price: String,
        val rate: String,
        val startDate: String,
        val hasLike: Boolean,
        val publishDate: String,
    ) : FavoriteUiItem()
}
