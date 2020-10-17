package com.example.todonotesapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todonotesapp.R

class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_notes_dialog_layout)
    }
}