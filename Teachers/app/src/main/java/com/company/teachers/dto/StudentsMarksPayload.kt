package com.company.teachers.dto

data class StudentsMarksPayload(val students: List<StudentsMarks>, val type: String)

data class StudentsMarks(val id: Int, val mark: String)

