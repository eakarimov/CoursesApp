package com.example.coursesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.domain.usecase.GetCoursesUseCase
import com.example.coursesapp.presentation.home.model.toHomeUiItemCourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    fun sortByPublishDate() {
        _uiState.value = _uiState.value.copy(
            courses = _uiState.value.courses.sortedByDescending { it.publishDate }
        )
    }

    private fun loadCourses() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val courses = getCoursesUseCase()

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                courses = courses.map { it.toHomeUiItemCourse() }
            )
        }
    }
}