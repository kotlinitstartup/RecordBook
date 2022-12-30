package com.company.teachers.dto

data class Teacher(
    val id: Int,
    var name: String = "",
    var surname: String = "",
    var subjects: List<String>,
    var groups: List<String>
)



