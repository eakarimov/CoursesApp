package com.example.coursesapp.domain.repository

import com.example.coursesapp.domain.model.Course

interface CoursesRepository {

    suspend fun getCourses(): List<Course>
}