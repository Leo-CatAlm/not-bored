package com.example.notbored.service

import com.example.notbored.model.ActivitiesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ActivitiesService {

    @GET("api/activity?participants={participants}")
    suspend fun getRandomActivity(@Path("participants") participants: Int): Response<ActivitiesResponse>

    @GET("api/activity?type={activity}&participants={participants}")
    suspend fun getActivity(@Path("activity") activity: String, @Path("participants") participants: Int): Response<ActivitiesResponse>
}