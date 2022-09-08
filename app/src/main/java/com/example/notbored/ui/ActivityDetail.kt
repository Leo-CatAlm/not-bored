package com.example.notbored.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notbored.R
import com.example.notbored.client.RetroFitClient
import com.example.notbored.databinding.ActivityDetailBinding
import com.example.notbored.model.ActivitiesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityDetail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val response = intent.getSerializableExtra("activityType") as ActivitiesResponse
        val isRandom = intent.getBooleanExtra("isRandom", false)
        binding.btnTryAnother.setOnClickListener {

            refreshActivity(response.participants,isRandom,response.type)
        }

        if (response.error=="Nothing") {
            setView(response, isRandom)
        } else {
            Toast.makeText(this, response.error, Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    private fun setView(response: ActivitiesResponse, isRandom: Boolean){
        with(binding) {
            tvActivityType.text = response.type.uppercase()
            tvActivityTitle.text = response.activity
            tvNumberOfParticipants.text = response.participants.toString()

            if (isRandom){
                tvRandomType.visibility = View.VISIBLE
                tvRandomType.text = response.type
            }

            when ((response.price.times(10)).toInt()) {
                0 -> tvAmount.setText(R.string.free)
                1, 2, 3 -> tvAmount.setText(R.string.low)
                4, 5, 6 -> tvAmount.setText(R.string.Medium)
                7, 8, 9 -> tvAmount.setText(R.string.high)
                else -> tvAmount.setText(R.string.cant_tell)
            }
        }

    }

    /**
     * This fun allows the user to get a new activity with the preview parameters that were used to get
     * the first activity
     * @param participants : the number of participants
     * @param isRandom : check if the intent to get the current activity is from random or not
     * @param type : get the type of the current activity to recall the api
     */
    private fun refreshActivity(participants: Int, isRandom: Boolean, type: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofitInstance = RetroFitClient.getInstance(RetroFitClient.BASE_URL)
            val call = when(isRandom) {
                true -> {
                    retrofitInstance.getRandomActivity(participants)

                }
                false -> {
                    retrofitInstance.getActivity(type, participants)
                }

            }
            runOnUiThread {
                if (call.isSuccessful) {
                    call.body()?.let { setView(it, isRandom) }
                } else {
                    Toast.makeText(
                        this@ActivityDetail,
                        call.body()?.error ?: "",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }


}



