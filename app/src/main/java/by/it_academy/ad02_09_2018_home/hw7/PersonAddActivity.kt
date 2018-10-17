package by.it_academy.ad02_09_2018_home.hw7

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import by.it_academy.ad02_09_2018_home.R

class PersonAddActivity : Activity() {
    private var imageEditText: EditText? = null
    private var nameEditText: EditText? = null
    private var surnameEditText: EditText? = null
    private var buttonSave: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_add)
        initViews()

        buttonSave?.setOnClickListener {
            val person = Person(imageEditText?.text.toString(), nameEditText?.text.toString(), surnameEditText?.text.toString())
            PersonListSingleton.list.add(person)
            var intent = Intent(this, Lesson7Activity::class.java)
            startActivity(intent)
        }
    }

    private fun initViews() {
        buttonSave = findViewById(R.id.save)
        imageEditText = findViewById(R.id.imageEditText)
        nameEditText = findViewById(R.id.nameEditText)
        surnameEditText = findViewById(R.id.surnameEditText)
    }

}