package com.lbd0.minigameparadise

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class baseballGame : AppCompatActivity() {


    var num : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.baseball)

        var start: Button = findViewById(R.id.start_btn)
        var plus :Button = findViewById(R.id.plus_btn)
        var score :TextView = findViewById(R.id.now_txt)


        start.setOnClickListener{


        }

        plus.setOnClickListener{
            ++num
            score.setText(""+num)

        }


    }
}