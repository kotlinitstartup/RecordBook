package com.company.teachers.dto


data class StudentsPayload(
    val groupId: Int,
    val semesterId: Int,
    val subjectId: Int,
    val type: String
)