package com.example.notbored.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notbored.databinding.ActivityTermBinding

class termActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTermBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTermBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}