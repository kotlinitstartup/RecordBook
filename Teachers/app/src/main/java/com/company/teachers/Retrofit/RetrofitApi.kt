package com.company.teachers.Retrofit

import android.util.Log
import androidx.fragment.app.Fragment
import com.company.teachers.dto.*
import com.company.teachers.utils.getFromSharedPreferences
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.file.attribute.AclEntry.newBuilder
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RetrofitApi(var fragment: Fragment) {

    private var BASE_URL = "https://record-books.onrender.com/"

    val client = OkHttpClient.Builder().connectTimeout(40000, TimeUnit.MILLISECONDS)
        .readTimeout(40000, TimeUnit.MILLISECONDS)
        .writeTimeout(40000, TimeUnit.MILLISECONDS)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
            val token = fragment.getFromSharedPreferences("token")
            if (token != null) {
                newRequest.addHeader("Authorization", token)
            }
            chain.proceed(newRequest.build())
        }
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service: Api = retrofit.create(Api::class.java)

    suspend fun login(teacherLoginPayload: TeacherLoginPayload): TeacherLoginResponse? {
        return suspendCoroutine { continuation ->
            val call = service.login(teacherLoginPayload)

            call.enqueue(object : Callback<TeacherLoginResponse> {
                    override fun onResponse(
                        call: Call<TeacherLoginResponse>,
                        response: Response<TeacherLoginResponse>
                    ) {
                        Log.i("login", response.code().toString())

                        continuation.resume(response.body())
                        Log.i("login", response.body().toString())
                    }
                    override fun onFailure(call: Call<TeacherLoginResponse>, t: Throwable) {
                        Log.i("login", "Login Failure")
                        Log.i("login", t.toString())
                        continuation.resumeWithException(t)
                    }
                })
        }

    }

    suspend fun getFilters(): FiltersResponse? {
        return suspendCoroutine { continuation ->
            val call = service.getFilters()
            call.enqueue(object : Callback<FiltersResponse> {
                override fun onResponse(
                    call: Call<FiltersResponse>,
                    response: Response<FiltersResponse>
                ) {

                    continuation.resume(response.body())
                }
                override fun onFailure(call: Call<FiltersResponse>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun getStudents(studentsPayload: StudentsPayload): List<StudentResponse>? {
        return suspendCoroutine { continuation ->
            val call = service.getStudents(mapOf(
                "groupId" to studentsPayload.groupId.toString(),
                "subjectId" to studentsPayload.subjectId.toString(),
                "semesterId" to studentsPayload.semesterId.toString(),
                "type" to studentsPayload.type
            ))

            call.enqueue(object : Callback<List<StudentResponse>> {
                override fun onResponse(
                    call: Call<List<StudentResponse>>,
                    response: Response<List<StudentResponse>>
                ) {
                    continuation.resume(response.body())
                }
                override fun onFailure(call: Call<List<StudentResponse>>, t: Throwable) {
                    Log.i("retrofit", t.toString())
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun updateStudents(stumarResponse: List<StudentsMarks>?): Unit{
        return suspendCoroutine { continuation ->
            val call = service.updateStudents(StudentsMarksPayload(stumarResponse!!, "exam"))
            call.enqueue(object : Callback<Unit> {
                override fun onResponse(
                    call: Call<Unit>,
                    response: Response<Unit>
                ) {
                    continuation.resume(Unit)
                }
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}

