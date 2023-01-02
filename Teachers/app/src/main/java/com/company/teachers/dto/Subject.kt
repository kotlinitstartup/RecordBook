package com.company.teachers.dto

data class Subject(
    val id: Number,
    val name: String


) {
    override fun toString(): String {
        return this.name
    }
}
