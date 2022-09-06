package com.example.notbored.client

import com.example.notbored.service.ActivitiesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitClient {

    companion object{
        private const val base_url = "https://www.boredapi.com"

        fun getInstance(url: String) : ActivitiesService {
            val retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ActivitiesService::class.java)
        }
    }
}