package com.company.teachers.dto

data class Group(
    val id: Number,
    val name: String
){
    override fun toString(): String {
        return this.name
    }
}
