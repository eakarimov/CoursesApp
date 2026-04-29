package com.example.coursesapp.presentation.home.model

import com.example.coursesapp.domain.model.Course
import java.text.SimpleDateFormat
import java.util.Locale

fun Course.toHomeUiItemCourse() = HomeUiItem.Course(
    id = id,
    title = title,
    text = description,
    price = price,
    rate = rate,
    startDate = startDate.toDate(),
    hasLike = hasLike,
    publishDate = publishDate
)

private fun String.toDate(): String {
    val parsedDate = SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.getDefault()
    ).parse(this) ?: this

    val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.forLanguageTag("ru"))
    val formattedDate = outputFormat.format(parsedDate)
    val splitted = formattedDate.split(" ").toMutableList()
    splitted[1] = splitted[1].replaceFirstChar { it.uppercase() }

    return splitted.joinToString(" ")
}