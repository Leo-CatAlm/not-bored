package com.example.notbored.model

import java.io.Serializable

data class ActivitiesResponse (
        val activity: String="",
        val type: String="Nothing",
        val participants: Int=0,
        val price: Double=0.0,
        val error: String="Nothing"
        ): Serializable