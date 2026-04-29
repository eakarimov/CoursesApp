package com.example.coursesapp.presentation.home.model

sealed class HomeUiItem {

    object Search: HomeUiItem()

    object Sort : HomeUiItem()

    data class Course(
        val id: Int,
        val title: String,
        val text: String,
        val price: String,
        val rate: String,
        val startDate: String,
        val hasLike: Boolean,
        val publishDate: String,
    ) : HomeUiItem()
}