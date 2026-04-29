package com.example.coursesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    @SerializedName("courses")
    val courses: List<CourseDTO>,
)
