package com.example.coursesapp.data.remote.mapper

import com.example.coursesapp.data.remote.dto.CourseDTO
import com.example.coursesapp.domain.model.Course

fun CourseDTO.toCourse() = Course(
    id = id,
    title = title,
    description = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate
)