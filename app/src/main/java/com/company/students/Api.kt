package com.company.students

import com.company.students.dto.Student
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("marks")
    fun getMarks(@Query("id") id: Number): Call<Student>
}