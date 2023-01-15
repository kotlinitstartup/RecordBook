package com.company.teachers.dto

import com.google.gson.annotations.SerializedName

data class StudentResponse(
    val mark: String,
    val student: Student
)