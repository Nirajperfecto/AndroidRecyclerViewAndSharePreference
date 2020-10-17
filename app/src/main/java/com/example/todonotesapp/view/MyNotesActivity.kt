package com.example.todonotesapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todonotesapp.utils.AppConstant
import com.example.todonotesapp.utils.PrefConstant
import com.example.todonotesapp.R
import com.example.todonotesapp.clickListners.ItemClickListner
import com.example.todonotesapp.model.Notes
import com.example.todonotesapp.adapter.NotesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MyNotesActivity : AppCompatActivity() {
    lateinit var fullName: String
    lateinit var fabActionButton: FloatingActionButton

    lateinit var sharedPreferences: SharedPreferences
    lateinit var recyclerViewNotes: RecyclerView
    var notesList = ArrayList<Notes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        bindView()
        setupSharedPreference()
        getIntentData()

        supportActionBar?.title = fullName

        fabActionButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setupDialogBox()
            }

        })
    }

    private fun setupDialogBox() {
        val view = LayoutInflater.from(this@MyNotesActivity).inflate(R.layout.add_notes_dialog_layout, null)
        val editTextTitle = view.findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextDescription)
        val buttonSubmit = view.findViewById<Button>(R.id.buttonSubmit)
        //Dialog
        val dialog = AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
        buttonSubmit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val title = editTextTitle.text.toString()
                val description = editTextDescription.text.toString()
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    val notes = Notes(title, description)
                    notesList.add(notes)
                } else {
                    Toast.makeText(this@MyNotesActivity, "Title or Description cannot be empty", Toast.LENGTH_SHORT).show()
                }
                setUpRecyclerView()
                dialog.hide()
            }


        })

        dialog.show()

    }

    private fun setUpRecyclerView() {
        val itemClickListener = object : ItemClickListner {

            override fun onClick(notes: Notes) {
                val intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(AppConstant.TITLE, notes.title)
                intent.putExtra(AppConstant.DESCRIPTION, notes.description)
                startActivity(intent)
            }

        }
        val notesAdapter = NotesAdapter(notesList, itemClickListener)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerViewNotes.layoutManager = linearLayoutManager
        recyclerViewNotes.adapter = notesAdapter
    }


    private fun getIntentData() {
        val intent = intent
        fullName = ""
        if (intent.hasExtra(AppConstant.FULL_NAME)) {
            fullName = intent.getStringExtra(AppConstant.FULL_NAME)
        }

        if (fullName.isEmpty()) {
            fullName = sharedPreferences.getString(PrefConstant.FULL_NAME, "").toString()
        }
    }

    private fun setupSharedPreference() {

        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    }

    private fun bindView() {
        fabActionButton = findViewById(R.id.fabActionButton)
        recyclerViewNotes = findViewById(R.id.recycleViewNotes)

    }
}





