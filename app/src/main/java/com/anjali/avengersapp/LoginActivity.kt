package com.anjali.avengersapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.anjali.avengersapp.savepreferences.LoginCredentials
import com.anjali.avengersapp.savepreferences.UsersPreference

class LoginActivity : AppCompatActivity()

{
    lateinit var etMobileNumber: EditText //varible name is same as that of id;s of respective variable in xml file,the data type mentioned there is created by the android studio SDK,when we perform tasks on this variable we 1st extract the value of this variable and then convert it to the data type that we know of
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button //to widen the scope of variable it is created before on create method since we need them anywhere in the class,hence they are the class variable and not the member variable
    lateinit var txtRegister: TextView

    var userPreferences = UsersPreference(this)
    val validMobileNumber = "0123456789"//we checking only against one value for logging in because app is not connected to the internet and we not have multiple users and their credentials
    val validPassword = arrayOf("My Fav Image", "Lonely", "Travelling", "At Fair")
    lateinit var sharedPreferences: SharedPreferences//variable of share...


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)//object of sh.. class inside oncreate method,having different syntax
        val isloggedin = sharedPreferences.getBoolean("isloggedin",false)//checking prefe..es for future login by reading from the file , value of key is always same as used below to write in the pref..es file it is case sensitive

        setContentView(R.layout.activity_login)
        if (isloggedin){
            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)
            startActivity(intent)
            finish()//to finish the login page if the user logged in for the 1st time
        }


        title = "Log In"

        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etpassword)  //declared inside to create and link the variables to their respective views as variables declared outside the oncreate method are not created
        btnLogin = findViewById(R.id.btnClickHere) //fin..byid mthod is defined in the SDK library of android,hence all the widgets and layouts must have ids
        txtRegister = findViewById(R.id.txtRegisterYourself)

//        for(i in LoginCredentials.VALID_MOBILE){
//
//
//            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)
//            startActivity(intent)
//
//
//        }

        btnLogin.setOnClickListener {

            val mobileNumber = etMobileNumber.text.toString()//views we have using till now are defined inside view class which has an interface called OnClickListener and this interface has a method called OnClick().another way to implement click listener,this provides the framework fo click listener,the way clicklistener added here is the lambda representation for setting clickli..er
            val password = etPassword.text.toString()//we cann't write this two statements before oncreate method bcoz we get the data only after the activity is created
            var nameOfAdventure ="Anjali's Journey"//var to store title of avengers page in accordance with entered password
            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)//source and destination will be given as parameter of intent class,kotlin is developed over java,bridge is created

            if ((mobileNumber == validMobileNumber) || (LoginCredentials.VALID_MOBILE.contains(mobileNumber)) ) {
                if (password == validPassword[0] || LoginCredentials.VALID_PASSWORD.contains(password)) {

                    nameOfAdventure = "Love This One"
                    //intent.putExtra("Name",nameOfAdventure),1st argument only have string data type while other has any,also 1st parameter is case sensitive
                    userPreferences.savePreferences(nameOfAdventure)// declared below as unless the variable has not any value iside it,the title gets stored in pref..es file now we have to extract it for the avengers activity in avengersactivity.kt file

                    startActivity(intent)// to start next activity once the bridge is created and pass object of class Intent as parameter

                } else if (password == validPassword[1]) {

                    nameOfAdventure = "Taking Selfie"
                    //intent.putExtra("Name",nameOfAdventure)
                    userPreferences.savePreferences(nameOfAdventure)

                    startActivity(intent)//we can not shift this code outside the if else block as code of intent bcoz we want the activity to start only if the credentials are correct
                } else if (password == validPassword[2]) {

                    nameOfAdventure = "My Fav Cloth"
                    //intent.putExtra("Name",nameOfAdventure)
                    userPreferences.savePreferences(nameOfAdventure)

                    startActivity(intent)

                } else if (password == validPassword[3]) {

                    nameOfAdventure = "Anjali's Journey"
                    //intent.putExtra("Name",nameOfAdventure)
                    userPreferences.savePreferences(nameOfAdventure)

                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@LoginActivity, "incorrect pasword",Toast.LENGTH_LONG).show()
                }
            }
            else {
                Toast.makeText(this@LoginActivity, "incorrect credential", Toast.LENGTH_LONG).show()

            }
        }

        txtRegister.setOnClickListener{

            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()


        }
    }

    override fun onPause() {// to prevent the login activity from opening again from paused state by pressing the back press with filled credentials by finishing it
        super.onPause()
        finish()//method used to finish activity once it go into paused state
    }
//    fun savePreferences(title:String){// fun to store boolean value for the users login status,i.e. true if if this is not the 1st login of the user and false unless outside oncreate myhod and will be called inside all the if else block from where the user get logged in
//        sharedPreferences.edit().putBoolean("isloggedin",true).apply()//edit mthod edit the file and put... mthod allow to save boolean value inside the file whose 1st parameter is string always,apply mthod is used to save the changes we have given
//        sharedPreferences.edit().putString("Title",title).apply()//commit mthod is also used but it will take more resources as it writes the data immediately, here we saved the prefe..es for the 1st login and for future login we have to check whether the boolean value is true or false and perform tasks accordingly,the second variable is same which we are using to save the tile of login activity
//    }

}

//shortcut to format the code: ctrl+alt+l
//to change the name of activity: select it and hit shift+f6
//sharedpreferences is a class in which data of app is stored via its object by pointing to a file where data is to be stored or to read from it
//an app will have multiple shared preferences file but we are creating here just one
//we will talk for shared...s as a file which stores data in our coding section
//the file saves the data in terms of key-value pair
//there are two methods to read data from the shared preferences file: getSharedPreferences(name of the file(stored as a string in the  res directory),mode which is usually private),getPreferences(mode,) as 2nd mthod is used when an application needs only one sharedpreferences file
//we use 1st method as we not know the upfront