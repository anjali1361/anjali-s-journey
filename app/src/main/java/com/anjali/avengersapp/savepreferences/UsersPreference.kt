package com.anjali.avengersapp.savepreferences

import android.content.Context
import android.provider.Settings.Secure.getString
import com.anjali.avengersapp.R

class UsersPreference(var baseContext:Context){



    fun savePreferences(title:String){

        val sharedPreferences = baseContext.getSharedPreferences(baseContext.getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isloggedin",true).apply()
        sharedPreferences.edit().putString("Title",title).apply()
    }


}