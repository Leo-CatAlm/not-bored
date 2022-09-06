package com.example.notbored.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.notbored.client.RetroFitClient
import com.example.notbored.databinding.ActivityDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TypesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)

        val numberParticipants:Int = intent.getStringExtra("participants")?.toInt() ?: 0




        val view = binding.root
        setContentView(view)

        val arrayAdapter: ArrayAdapter<*>
        val activityTypes = arrayOf(
            "education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork"
        )

        arrayAdapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1, activityTypes)

        with(binding){
            lvActivities.adapter = arrayAdapter
            lvActivities.setOnItemClickListener { adapterView, view, i, l ->
                val activityType: String = adapterView.getItemAtPosition(i) as String
                suggestGivenActivity(activityType, numberParticipants)
            }
        }

    }

    private fun suggestGivenActivity(type: String, participants: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetroFitClient.getInstance(RetroFitClient.BASE_URL).getActivity(type, participants)
            val activity = call.body()
            if (call.isSuccessful){
                val goDetail = Intent(parent, ActivityDetail::class.java)
                //TODO: Make ActivitiesResponse Serializable
                /*goDetail.putExtra("activityType", activity)*/

                startActivity(goDetail)
            } else {
                Log.e("Call", call.errorBody().toString())
            }
        }
    }
}