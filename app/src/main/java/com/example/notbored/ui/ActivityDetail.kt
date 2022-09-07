package com.example.notbored.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notbored.databinding.ActivityDetailBinding
import com.example.notbored.model.ActivitiesResponse

class ActivityDetail: AppCompatActivity()  {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val response = intent.getSerializableExtra("activityType") as ActivitiesResponse

        with(binding){
            tvActivityType.text = response.type
            tvActivityTitle.text = response.activity
            tvNumberOfParticipants.text = response.participants.toString()

            when(response.price){
                0.0 -> tvPrice.text = "Free"
                /*0 .. 0.3 -> tvPrice.text = "Low"
                0 .. 0.3 -> tvPrice.text = "Medium"
                0 .. 0.3 -> tvPrice.text = "High"*/
            }
        }
    }
}