package com.example.workmeout.ui.me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workmeout.Controlador.Controlador
import com.example.workmeout.R

class SearchExercises : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_exercises)

        val recycler:RecyclerView = findViewById<RecyclerView>(R.id.rv)       
        
        val adapter = ExerciseSearchAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        

        val edit: EditText = findViewById(R.id.editText_search)
        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Controlador.matchExercise(baseContext, edit.text.toString(), adapter)

            }
        })

    }
}