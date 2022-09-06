package com.example.notbored.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notbored.R
import com.example.notbored.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setListeners()
        setContentView(view)
    }

    fun setListeners(){
        binding.btnStart.setOnClickListener {

            val numberParticipants=binding.etNumber.text
            when{
                numberParticipants.isNullOrEmpty()-> Toast.makeText(baseContext,R.string.emptyParticipans,Toast.LENGTH_LONG).show()
                !isInteger(numberParticipants.toString())-> Toast.makeText(this,R.string.typeParticipans,Toast.LENGTH_LONG).show()
                else-> {
                    Toast.makeText(this,R.string.loading,Toast.LENGTH_LONG).show()
                    val goDetail= Intent(this, TypesActivity::class.java)
                    goDetail.putExtra("participants",numberParticipants)
                    startActivity(goDetail)
                }
            }

        }

        binding.tvTerm.setOnClickListener {
            val goTerm= Intent(this, TermActivity::class.java)
            startActivity(goTerm)

            //Ir a la otra actividad
        }

    }
    fun isInteger(s: String): Boolean {
        return try {
            s.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}