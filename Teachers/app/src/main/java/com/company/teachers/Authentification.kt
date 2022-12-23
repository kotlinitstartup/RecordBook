package com.company.teachers

import com.company.teachers.Retrofit.RetrofitApi
import com.company.teachers.dto.Teacher
import com.company.teachers.dto.TeacherLoginPayload

class Authentification private constructor() {

     var currentTeacher: Teacher? = null
     lateinit var retrofitApi: RetrofitApi

    companion object {
        @Volatile
        private lateinit var instance: Authentification

        fun getInstance(): Authentification {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = Authentification()
                }
                return instance
            }
        }
    }

    fun Login(login: String, password: String): Boolean{
        val teacherLoginPayload = TeacherLoginPayload(login, password)
        retrofitApi = RetrofitApi()
        retrofitApi.Login(teacherLoginPayload)

        return false
    }

    fun signOut(){

    }

}