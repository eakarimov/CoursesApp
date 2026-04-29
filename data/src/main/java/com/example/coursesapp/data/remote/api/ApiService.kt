package com.example.coursesapp.data.remote.api

import com.example.coursesapp.data.remote.dto.CoursesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("courses")
    suspend fun getCourses(): CoursesResponse
}