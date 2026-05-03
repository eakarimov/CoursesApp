package com.example.coursesapp.presentation.favorite

import com.example.coursesapp.presentation.favorite.model.FavoriteUiItem

data class FavoriteUiState(
    val courses: List<FavoriteUiItem.Course> = emptyList(),
)