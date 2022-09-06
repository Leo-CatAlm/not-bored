package com.example.notbored.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.notbored.databinding.ActivityDetailsBinding
import com.example.notbored.databinding.ActivityTermBinding


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

        binding.lvActivities.adapter = arrayAdapter
    }
}