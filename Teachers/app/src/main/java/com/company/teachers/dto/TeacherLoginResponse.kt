package com.company.teachers.dto

data class User(val id: Int, val firstname: String, val lastname: String)

data class TeacherLoginResponse(val user: User, val token: String)

