package com.company.students.dto

data class StudentLoginResponse(
    val user: Student,
    val token: String
)
