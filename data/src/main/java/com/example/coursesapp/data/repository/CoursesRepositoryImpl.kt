package com.example.coursesapp.data.repository

import com.example.coursesapp.data.remote.api.ApiService
import com.example.coursesapp.data.remote.mapper.toCourse
import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.domain.repository.CoursesRepository
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val api: ApiService
) : CoursesRepository {

    override suspend fun getCourses(): List<Course> =
        api.getCourses().courses.map { it.toCourse() }
}