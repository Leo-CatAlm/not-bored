package com.example.notbored.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notbored.R
import com.example.notbored.databinding.ActivityDetailBinding
import com.example.notbored.model.ActivitiesResponse

class ActivityDetail: AppCompatActivity()  {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val response = intent.getSerializableExtra("activityType") as ActivitiesResponse

        if(response.error.equals("Nothing")){
            with(binding){
                tvActivityType.text = response.type
                tvActivityTitle.text = response.activity
                tvNumberOfParticipants.text = response.participants.toString()

                when((response.price.times(10)).toInt()){
                    0 -> tvAmount.setText(R.string.free)
                    1,2,3 -> tvAmount.setText(R.string.low)
                    4,5,6 -> tvAmount.setText(R.string.Medium)
                    7,8,9 -> tvAmount.setText(R.string.high)
                    else -> tvAmount.setText(R.string.cant_tell)
                }
            }
        }else{
            Toast.makeText(this,response.error,Toast.LENGTH_LONG).show()
            finish()
        }

    }
}