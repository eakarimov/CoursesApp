package com.example.coursesapp.domain.usecase

import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.domain.repository.CoursesRepository

class GetCoursesUseCase (
    private val coursesRepository: CoursesRepository
) {

    suspend operator fun invoke(): List<Course> = coursesRepository.getCourses()
}