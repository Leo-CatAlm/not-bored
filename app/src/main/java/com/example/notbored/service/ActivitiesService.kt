package com.example.notbored.service

import com.example.notbored.model.ActivitiesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ActivitiesService {

    @GET("api/activity")
    suspend fun getRandomActivity(@Query("participants") participants: Int): Response<ActivitiesResponse>

    @GET("api/activity")
    suspend fun getActivity(@Query("type") activity: String, @Query("participants") participants: Int): Response<ActivitiesResponse>
}