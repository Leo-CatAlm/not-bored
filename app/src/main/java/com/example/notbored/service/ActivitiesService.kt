package com.example.notbored.service

import retrofit2.http.GET
import retrofit2.http.Path

interface ActivitiesService {

    //TODO: add Call<T> in both gets
    @GET("/api/activity?participants={participants}")
    fun getRandomActivity(@Path("participants") participants: Int)

    @GET("api/activity?type={activity}&participants={participants}")
    fun getActivity(@Path("activity") activity: String, @Path("participants") participants: Int)
}