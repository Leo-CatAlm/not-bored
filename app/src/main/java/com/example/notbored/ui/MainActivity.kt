package com.example.notbored.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notbored.R
import com.example.notbored.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setListeners()
        setContentView(view)
    }

    /**
     * Set the listeners of the start button and the terms hyperText.
     *  - The start buttons retrieve messages in case the user don't give a number of participants or gives it with a wrong format
     *  - The terms hyperText send the user to a screen with everything that the user should accept to continue
     */
    fun setListeners(){
        binding.btnStart.setOnClickListener {
            val checkBox = binding.cbTermsAndConditions
            val numberParticipants=binding.etNumber.text
            when{
                numberParticipants.isNullOrEmpty()-> Toast.makeText(baseContext,R.string.empty_participants,Toast.LENGTH_SHORT).show()
                !isInteger(numberParticipants.toString())-> Toast.makeText(this,R.string.type_participants,Toast.LENGTH_SHORT).show()
                !checkBox.isChecked -> Toast.makeText(this,R.string.must_accept_terms,Toast.LENGTH_SHORT).show()
                else-> {
                    Toast.makeText(this,R.string.loading,Toast.LENGTH_SHORT).show()
                    val goDetail= Intent(this, TypesActivity::class.java)
                    goDetail.putExtra("participants",numberParticipants.toString().toInt())
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

    /**
     * This fun allows us to control if the user put a number or not
     * @param s : This is what the user write in the participants bar
     */
    fun isInteger(s: String): Boolean {
        return try {
            s.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}