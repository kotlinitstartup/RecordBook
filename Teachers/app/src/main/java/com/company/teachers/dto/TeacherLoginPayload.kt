package com.company.teachers.dto

import com.google.gson.annotations.SerializedName

data class TeacherLoginPayload(@SerializedName("email") val email: String,
                               @SerializedName("password")val password: String)