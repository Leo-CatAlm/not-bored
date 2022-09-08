package com.example.notbored.service

import com.example.notbored.model.ActivitiesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ActivitiesService {

    /**
     * It retrieves a random activity to the user, only asking for the number of participants
     * @param participants : the number of participants
     */
    @GET("api/activity")
    suspend fun getRandomActivity(@Query("participants") participants: Int): Response<ActivitiesResponse>

    /**
     * It retrieves an activity considering the subject and the number of participants
     * @param participants : the number of participants
     * @param type : the type of the activity
     */
    @GET("api/activity")
    suspend fun getActivity(@Query("type") activity: String, @Query("participants") participants: Int): Response<ActivitiesResponse>
}