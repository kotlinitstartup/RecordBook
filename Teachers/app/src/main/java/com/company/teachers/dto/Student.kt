package com.company.teachers.dto

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val recordBook: RecordBook,
    val firstname: String,
    val lastname: String,
)

