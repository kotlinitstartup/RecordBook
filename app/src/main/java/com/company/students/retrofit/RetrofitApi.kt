package com.company.students.retrofit

import androidx.fragment.app.Fragment
import com.company.students.dto.MarkRecord
import com.company.students.dto.StudentLoginPayload
import com.company.students.dto.StudentLoginResponse
import com.company.students.utils.getFromSharedPreferences
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.suspendCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class RetrofitApi(var fragment: Fragment) {
    private var BASE_URL = "https://record-books.onrender.com/"
//private var BASE_URL = "http://10.0.2.2:8000/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(20000, TimeUnit.MILLISECONDS)
        .readTimeout(20000, TimeUnit.MILLISECONDS)
        .writeTimeout(20000, TimeUnit.MILLISECONDS)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
            val token = fragment.getFromSharedPreferences("token")
            if (token != null) {
                newRequest.addHeader("Authorization", token)
            }
            chain.proceed(newRequest.build())
        }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val service: Api = retrofit.create(Api::class.java)

    suspend fun login(recordBookNumber: String): StudentLoginResponse? {
        return suspendCoroutine { continuation ->
            val call = service.login(StudentLoginPayload(recordBookNumber))

            call.enqueue(object : Callback<StudentLoginResponse> {
                override fun onResponse(
                    call: Call<StudentLoginResponse>,
                    response: Response<StudentLoginResponse>
                ) {
                    continuation.resume(response.body())
                }

                override fun onFailure(call: Call<StudentLoginResponse>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun getMarks(semesterId: Number, type: String): List<MarkRecord>? {
        return suspendCoroutine { continuation ->
            val call = service.getMarks(mapOf(
                "semesterId" to semesterId.toString(),
                "type" to type
            ))

            call.enqueue(object : Callback<List<MarkRecord>> {
                override fun onResponse(
                    call: Call<List<MarkRecord>>,
                    response: Response<List<MarkRecord>>
                ) {
                    continuation.resume(response.body())
                }

                override fun onFailure(call: Call<List<MarkRecord>>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}