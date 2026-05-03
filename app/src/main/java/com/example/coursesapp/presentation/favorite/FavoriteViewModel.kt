package com.example.coursesapp.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.domain.usecase.GetFavoriteCoursesUseCase
import com.example.coursesapp.presentation.favorite.model.toFavoriteUiItemCourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteCoursesUseCase: GetFavoriteCoursesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadFavoriteCourses()
    }

    private fun loadFavoriteCourses() {
        viewModelScope.launch {
            val courses = getFavoriteCoursesUseCase()

            _uiState.value = _uiState.value.copy(
                courses = courses.map { it.toFavoriteUiItemCourse() }
            )
        }
    }
}