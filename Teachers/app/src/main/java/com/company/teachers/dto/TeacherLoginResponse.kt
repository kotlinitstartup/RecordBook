package com.company.teachers.dto

data class TeacherLoginResponse(val user: User, val token: String) {
}

data class User(val id: Int, val firstname: String, val lastname: String)