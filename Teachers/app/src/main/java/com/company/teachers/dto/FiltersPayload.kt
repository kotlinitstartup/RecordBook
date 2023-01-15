package com.company.teachers.dto

import com.google.gson.annotations.SerializedName

data class FiltersPayload(@SerializedName("groupId") val groupId: Int,
                     @SerializedName("semesterId") val semesterId: Int,
                     @SerializedName("subjectId") val subjectId: Int,
                     @SerializedName("type") val type: String)
