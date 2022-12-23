package com.company.teachers.Retrofit

import com.company.teachers.dto.Teacher
import com.company.teachers.dto.TeacherLoginPayload
import com.company.teachers.dto.TeacherLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST("auth/teachers/login")
    fun login(@Body teacherLoginPayload: TeacherLoginPayload): Call<TeacherLoginResponse>

}