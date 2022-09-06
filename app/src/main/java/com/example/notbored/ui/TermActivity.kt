package com.example.notbored.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notbored.databinding.ActivityTermBinding

class TermActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTermBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTermBinding.inflate(layoutInflater)
        val view = binding.root

        binding.ivCancel.setOnClickListener {
            //val backMain= Intent(this, MainActivity::class.java)
            //startActivity(backMain)
            finish()
        }
        setContentView(view)
    }
}