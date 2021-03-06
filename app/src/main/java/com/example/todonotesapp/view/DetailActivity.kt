package com.example.todonotesapp.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todonotesapp.utils.AppConstant
import com.example.todonotesapp.R

class DetailActivity : AppCompatActivity() {
  lateinit var textViewTitle : TextView
    lateinit var textViewDescription : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindView()
        getIntentData()
    }

    private fun getIntentData() {
        val intent = intent //getIntent//
        val title : String = intent.getStringExtra(AppConstant.TITLE)
        val description : String = intent.getStringExtra(AppConstant.DESCRIPTION)
        //setText()
        textViewTitle.text = title
        textViewDescription.text = description
    }

    private fun bindView() {
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewDescription = findViewById(R.id.textViewDescription)
    }
}