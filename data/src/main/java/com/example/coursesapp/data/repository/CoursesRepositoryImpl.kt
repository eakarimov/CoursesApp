package com.example.coursesapp.data.repository

import com.example.coursesapp.data.remote.api.ApiService
import com.example.coursesapp.data.remote.mapper.toCourse
import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.domain.repository.CoursesRepository
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val api: ApiService
) : CoursesRepository {

    var cachedCourses: List<Course>? = null

    override suspend fun getCourses(): List<Course> {
        cachedCourses?.let { return it }

        val courses = api.getCourses().courses.map { it.toCourse() }
        cachedCourses = courses

        return courses
    }
}