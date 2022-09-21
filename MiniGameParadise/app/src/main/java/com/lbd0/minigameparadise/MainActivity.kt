package com.lbd0.minigameparadise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var select :Button = findViewById(R.id.selectbnt)
        var start  : Button = findViewById(R.id.startbnt)


        select.setOnClickListener{
            var intent : Intent = Intent(this,CharacterChoice::class.java)
            startActivity(intent)
        }

        start.setOnClickListener{
            var intent : Intent = Intent(this, GameChoice::class.java)
            startActivity(intent)
        }

    }
}