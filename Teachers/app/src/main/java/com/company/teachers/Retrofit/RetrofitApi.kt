package com.company.teachers.Retrofit

import android.util.Log
import com.company.teachers.dto.TeacherLoginPayload
import com.company.teachers.dto.TeacherLoginResponse
import com.company.teachers.dto.User
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    private var BASE_URL = "https://record-books.onrender.com/"

    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer")
                .build()
            chain.proceed(newRequest)
        }
        .build()

    val scope = CoroutineScope(Dispatchers.IO)

    suspend fun login(teacherLoginPayload: TeacherLoginPayload): TeacherLoginResponse? {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: Api = retrofit.create(Api::class.java)

        val teacherLoginResponse = CompletableDeferred<TeacherLoginResponse?>()

        val call = service.login(teacherLoginPayload)

        scope.launch {
            call.enqueue(object : Callback<TeacherLoginResponse> {
                override fun onResponse(
                    call: Call<TeacherLoginResponse>,
                    response: Response<TeacherLoginResponse>
                ) {
                    Log.i("login", response.code().toString())
                    if (response.isSuccessful) {
                        teacherLoginResponse.complete(response.body())
                        Log.i("login", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<TeacherLoginResponse>, t: Throwable) {
                    Log.i("login", "Login Failure")
                    Log.i("login", call.toString())
                }
            })
        }
        return teacherLoginResponse.await()
    }
}

