package com.company.teachers.Retrofit

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.company.teachers.dto.FiltersResponse
import com.company.teachers.dto.TeacherLoginPayload
import com.company.teachers.dto.TeacherLoginResponse
import com.company.teachers.dto.User
import com.company.teachers.utils.getFromSharedPreferences
import kotlinx.coroutines.*
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Route
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.file.attribute.AclEntry.newBuilder
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RetrofitApi(var fragment: Fragment) {

    private var BASE_URL = "https://record-books.onrender.com/"

    val client = OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS)
        .readTimeout(10000, TimeUnit.MILLISECONDS)
        .writeTimeout(10000, TimeUnit.MILLISECONDS)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", fragment.getFromSharedPreferences("token"))
                .build()
            chain.proceed(newRequest)
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
}

