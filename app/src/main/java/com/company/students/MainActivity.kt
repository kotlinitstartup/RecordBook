package com.company.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.company.students.dto.Student
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/students/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val studentsApi = retrofit.create(Api::class.java)

        val userCall = studentsApi.getMarks(1)
        userCall.enqueue(object : Callback<Student> {
            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                Log.i("Govno", response.body()?.firstname.toString())
            }

            override fun onFailure(call: Call<Student>, t: Throwable) {
                Log.i("Pomoyka", t.message.toString())
            }
        })
    }
}