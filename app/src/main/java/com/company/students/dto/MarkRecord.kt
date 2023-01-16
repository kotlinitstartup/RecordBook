package com.company.students.dto

data class MarkRecord(
    val id: Number,
    val mark: String,
    val createdAt: String,
    val updatedAt: String,
    val semester: Semester,
    val subject: Subject,
    val teacher: Teacher,
    val student: Student,
)
