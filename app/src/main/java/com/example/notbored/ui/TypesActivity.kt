package com.example.notbored.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.notbored.client.RetroFitClient
import com.example.notbored.databinding.ActivityTypesBinding
import com.example.notbored.model.ActivitiesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TypesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTypesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTypesBinding.inflate(layoutInflater)

        val numberParticipants = intent.getIntExtra("participants",0)
        println("numberParticipants  $numberParticipants")



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
            ivRandom.setOnClickListener {
                suggestRandomActivity(numberParticipants)
            }

        }

    }

    /**
     * This fun suggest a random activity (as the name says). It receives the number of participants that were given in the previous screen
     * With the Activity that was retrieved the app will send the user to the next screen to see the details
     * @param participants : the number of participants
     */
    fun suggestRandomActivity(participants: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetroFitClient.getInstance(RetroFitClient.BASE_URL).getRandomActivity(participants)
            val activity: ActivitiesResponse? = call.body()
            if (call.isSuccessful){
                val goDetail = Intent(this@TypesActivity, ActivityDetail::class.java)
                goDetail.putExtra("activityType", activity)
                goDetail.putExtra("isRandom", true)
                println("Activity $activity")

                startActivity(goDetail)
            } else {
                Log.e("Call", call.errorBody().toString())
            }
        }
    }

    /**
     * This fun suggest a given activity (as the name says). It receives the number of participants that were
     * given in the previous screen, and the type of activity given in the current screen.
     * With the Activity that was retrieved the app will send the user to the next screen to see the details
     * @param participants : the number of participants
     * @param type :  the type of activity that will be suggested
     */
     fun suggestGivenActivity(type: String, participants: Int){
        println("type $type $participants")
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetroFitClient.getInstance(RetroFitClient.BASE_URL).getActivity(type, participants)
            val activity: ActivitiesResponse? = call.body()
            if (call.isSuccessful){
                val goDetail = Intent(this@TypesActivity, ActivityDetail::class.java)
                goDetail.putExtra("activityType", activity)
                goDetail.putExtra("isRandom", false)
                println("Activity $activity")

                startActivity(goDetail)
            } else {
                Log.e("Call", call.errorBody().toString())
            }
        }
    }
}