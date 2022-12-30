package com.company.teachers

import com.company.teachers.Retrofit.RetrofitApi
import com.company.teachers.dto.Teacher
import com.company.teachers.dto.TeacherLoginPayload
import kotlin.coroutines.suspendCoroutine

class Authentification private constructor() {

     lateinit var retrofitApi: RetrofitApi
     var token: String? = null

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

     suspend fun login(email: String, password: String){
            val teacherLoginPayload = TeacherLoginPayload(email, password)
            retrofitApi = RetrofitApi()
            val teacherLoginResponse = retrofitApi.login(teacherLoginPayload)
            token = teacherLoginResponse?.token
    }

    fun signOut(){

    }

}