package com.example.notbored.model

import java.io.Serializable

data class ActivitiesResponse (
        val activity: String,
        val type: String,
        val participants: Int,
        val price: Double
        ): Serializable