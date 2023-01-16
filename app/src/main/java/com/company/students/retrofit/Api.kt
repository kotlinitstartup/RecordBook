package com.company.students.retrofit

import com.company.students.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface Api {
    @POST("auth/students/login")
    fun login(@Body studentLoginPayload: StudentLoginPayload): Call<StudentLoginResponse>

    @GET("students/marks")
    fun getMarks(@QueryMap getMarksQueryMap: Map<String, String>): Call<List<MarkRecord>>
}