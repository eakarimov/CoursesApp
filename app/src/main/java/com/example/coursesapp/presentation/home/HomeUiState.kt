package com.example.coursesapp.presentation.home

import com.example.coursesapp.presentation.home.model.HomeUiItem

data class HomeUiState(
    val isLoading: Boolean = true,
    val courses: List<HomeUiItem.Course> = emptyList(),
)