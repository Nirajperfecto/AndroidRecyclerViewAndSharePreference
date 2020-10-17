package com.example.todonotesapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todonotesapp.utils.AppConstant
import com.example.todonotesapp.utils.PrefConstant
import com.example.todonotesapp.R

class LoginActivity : AppCompatActivity(){
    lateinit var editTextFullName : EditText
    lateinit var editTextUserName : EditText
    lateinit var login : Button
    lateinit var sharedPreferences : SharedPreferences
    lateinit var  editor : SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextFullName = findViewById(R.id.editTextFullName)
        editTextUserName = findViewById(R.id.editTextUserName)
        login = findViewById(R.id.login)

        setupSharedPreferences()

        val clickAction = object : View.OnClickListener{
            override fun onClick(v: View?) {
                val fullName = editTextFullName.text.toString()
                val userName = editTextUserName.text.toString()
                //FullName and UserName != Empty
                if(fullName.isNotEmpty() && userName.isNotEmpty()){
                    val intent = Intent (this@LoginActivity, MyNotesActivity::class.java)
                    intent.putExtra(AppConstant.FULL_NAME, fullName)
                    startActivity(intent)
                    //Login
                    //Login
                    saveLoginStatus()
                    saveFullName(fullName)

                }
                else{
                    Toast.makeText(this@LoginActivity, "fullName and UserName can't be Empty", Toast.LENGTH_SHORT).show()

                }
            }

        }

        login.setOnClickListener(clickAction)

    }

    private fun saveFullName(fullName: String) {
        editor = sharedPreferences.edit()
        editor.putString(PrefConstant.FULL_NAME, fullName)
        editor.apply()

    }

    private fun saveLoginStatus() {
        editor = sharedPreferences.edit()
        editor.putBoolean(PrefConstant.IS_LOGGED_IN, true)
        editor.apply()

    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}