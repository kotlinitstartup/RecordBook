package com.company.teachers.Retrofit

import com.company.teachers.dto.FiltersPayload
import com.company.teachers.dto.FiltersResponse
import com.company.teachers.dto.TeacherLoginPayload
import com.company.teachers.dto.TeacherLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface Api {
    @POST("auth/teachers/login")
    fun login(@Body teacherLoginPayload: TeacherLoginPayload): Call<TeacherLoginResponse>

    @GET("teachers/filters")
    fun getFilters(): Call<FiltersResponse>

    @GET("teachers/students")
    fun getStudents(@QueryMap filtersPayload: FiltersPayload): Call<FiltersResponse>
}