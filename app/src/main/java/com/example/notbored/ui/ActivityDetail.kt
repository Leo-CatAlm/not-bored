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

            when((response.price * 10).toInt()){

                0 -> tvAmount.text = "Free"
                1,2,3 -> tvAmount.text = "Low"
                4,5,6 -> tvAmount.text = "Medium"
                7,8,9 -> tvAmount.text = "High"

                else -> tvAmount.text = "Can't tell"
            }
        }
    }
}