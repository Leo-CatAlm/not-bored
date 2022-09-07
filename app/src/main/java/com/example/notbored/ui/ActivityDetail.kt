package com.example.notbored.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notbored.R
import com.example.notbored.client.RetroFitClient
import com.example.notbored.databinding.ActivityDetailBinding
import com.example.notbored.model.ActivitiesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityDetail: AppCompatActivity()  {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberParticipants = intent.getIntExtra("participants",0)
        val response = intent.getSerializableExtra("activityType") as ActivitiesResponse

        binding.btnTryAnother.setOnClickListener {
            refreshActivity(numberParticipants)
        }

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
 private fun refreshActivity(participants: Int){
     CoroutineScope(Dispatchers.IO).launch{
         val call = RetroFitClient.getInstance(RetroFitClient.BASE_URL).getRandomActivity(participants)
         val activities: ActivitiesResponse? = call.body()
         runOnUiThread {
             if (call.isSuccessful){
                 with(binding){
                     if (activities != null) {
                         tvActivityType.text = activities.type
                         tvActivityTitle.text = activities.activity
                         tvNumberOfParticipants.text = activities.participants.toString()
                     }
                 }
             }else{
                 if (activities != null) {
                     Toast.makeText(this@ActivityDetail,activities.error,Toast.LENGTH_LONG).show()
                 }
             }
         }

     }


 }


}
