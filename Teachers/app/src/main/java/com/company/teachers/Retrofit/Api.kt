package com.company.teachers.Retrofit

import com.company.teachers.dto.*
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @POST("auth/teachers/login")
    fun login(@Body teacherLoginPayload: TeacherLoginPayload): Call<TeacherLoginResponse>

    @GET("teachers/filters")
    fun getFilters(): Call<FiltersResponse>

    @GET("teachers/students")
    fun getStudents(@QueryMap studentsPayload: Map<String, String>): Call<List<StudentResponse>>

    @PUT("teachers/students/marks")
    fun updateStudents(@Body p: StudentsMarksPayload): Call<Unit>
}