package com.company.teachers.Retrofit

import com.company.teachers.dto.Teacher
import com.company.teachers.dto.TeacherLoginPayload
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitApi {

    private lateinit var teacher: Teacher

    private val retrofit: Api = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java)

    fun Login(teacherLoginPayload: TeacherLoginPayload){

    }

}

