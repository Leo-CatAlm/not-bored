package com.example.notbored.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notbored.databinding.ActivityDetailsBinding
import com.example.notbored.databinding.ActivityTermBinding


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)

        val numberParticipants:Int = intent.getStringExtra("participants")?.toInt() ?: 0




        val view = binding.root
        setContentView(view)
    }
}