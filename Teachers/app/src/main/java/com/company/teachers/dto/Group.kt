package com.company.teachers.dto

data class Group(
    val id: Number,
    val name: String,
    val course: Number
){
    override fun toString(): String {
        return name
    }
}
