package com.example.project
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.LinearLayout
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText



class MainActivity : AppCompatActivity() {
    private lateinit var editTextTask: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

        val buttonAddSection = findViewById<Button>(R.id.b1)
        val layoutRoot = findViewById<LinearLayout>(R.id.main)
        editTextTask = findViewById(R.id.taskText)

        buttonAddSection.setOnClickListener {
            addNewSection(layoutRoot, editTextTask)
            editTextTask.setText("")
        }
        }
    private fun addNewSection(layoutRoot: LinearLayout, editText: EditText) {
        val inflater = layoutInflater
        val sectionView = inflater.inflate(R.layout.section_layout, null)

        val removeSectionButton = sectionView.findViewById<Button>(R.id.remove_section_button)
        removeSectionButton.setOnClickListener {
            // Usuń sekcję z głównego layoutu
            layoutRoot.removeView(sectionView)
        }

        val editSectionButton = sectionView.findViewById<Button>(R.id.edit_section_button)
        editSectionButton.setOnClickListener {
            // Otwórz dialog do edycji tekstu sekcji
            val editTextDialog = AlertDialog.Builder(this)
                .setTitle("Edytuj sekcję")
                .setMessage("Wprowadź nowy tekst:")

            // Dodaj pole tekstowe do dialogu
            val input = EditText(this)
            editTextDialog.setView(input)

            editTextDialog.setPositiveButton("Zapisz") { dialog, which ->
                val newSectionText = input.text.toString()
                // Aktualizuj tekst sekcji
                val sectionTextView = sectionView.findViewById<TextView>(R.id.section_text)
                sectionTextView.text = newSectionText
            }
            editTextDialog.setNegativeButton("Anuluj", null)
            editTextDialog.create().show()
        }



        val sectionTextView = sectionView.findViewById<TextView>(R.id.section_text)
        sectionTextView.text = editTextTask.text.toString()

        layoutRoot.addView(sectionView)
    }
    }