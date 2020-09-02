package com.anjali.avengersapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.anjali.avengersapp.savepreferences.LoginCredentials
import com.anjali.avengersapp.savepreferences.UsersPreference

class RegisterActivity : AppCompatActivity() {

    lateinit var mobile:EditText
    lateinit var password:EditText
    lateinit var done:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mobile = findViewById(R.id.mobile)
        password = findViewById(R.id.password)
        done = findViewById(R.id.done)

        done.setOnClickListener {

            val Mobile = mobile.text.toString()
            val Password = password.text.toString()

//            userPreferences.newPreferences(Mobile,Password)

            if(Mobile.length == 10) {

                LoginCredentials.VALID_MOBILE.add(Mobile)
                LoginCredentials.VALID_PASSWORD.add(Password)
            }

            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}
