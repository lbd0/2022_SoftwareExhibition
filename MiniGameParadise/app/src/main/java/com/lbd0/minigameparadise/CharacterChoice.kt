package com.lbd0.minigameparadise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CharacterChoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_choice)

        var character : ImageView = findViewById(R.id.ani_rat)
        val choice : Button =  findViewById<Button>(R.id.choicebtn)

        choice.setOnClickListener {
            var intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}