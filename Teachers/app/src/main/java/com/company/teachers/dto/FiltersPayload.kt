package com.company.teachers.dto

import com.google.gson.annotations.SerializedName

data class FiltersPayload(@SerializedName("groupId") val groupId: Number,
                     @SerializedName("semesterId") val semesterId: Number,
                     @SerializedName("subjectId") val subjectId: Number,
                     @SerializedName("type") val type: String)
