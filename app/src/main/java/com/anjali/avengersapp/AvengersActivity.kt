package com.anjali.avengersapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AvengersActivity : AppCompatActivity() {
  lateinit var etMessage:EditText
    lateinit var btnSendMessage:Button
    lateinit var btnLogout:Button

    var titleName: String? = "Anjali's Journey " //to store title we extracted from sharedpreferences file
    lateinit var sharedPreferences: SharedPreferences//same steps should be repeated as done in loginactivty.kt file to get data from file for this activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        setContentView(R.layout.activity_avengers)

        titleName = sharedPreferences.getString("Title","Anjali's Journey")// default value is given for those avengers who dont have their own password


        title = titleName //extracted title

        etMessage = findViewById(R.id.etMessage)
        btnSendMessage = findViewById(R.id.btnSendMessage)
        btnLogout = findViewById(R.id.btnLogout)
        btnSendMessage.setOnClickListener{

            val  intent = Intent(this@AvengersActivity,MessageActivity::class.java)
            val message = etMessage.text.toString()
            intent.putExtra("Message",message)
            startActivity(intent)
        }
        btnLogout.setOnClickListener{
            startActivity(Intent(this@AvengersActivity,LoginActivity::class.java))
            sharedPreferences.edit().clear().apply()
            finish()
        }
    }

}