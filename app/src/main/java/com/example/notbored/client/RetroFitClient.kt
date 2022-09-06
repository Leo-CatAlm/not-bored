package com.example.notbored.client

import com.example.notbored.service.ActivitiesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {

      const val BASE_URL = "http://www.boredapi.com/"

        fun getInstance(url: String) : ActivitiesService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ActivitiesService::class.java)
        }

}