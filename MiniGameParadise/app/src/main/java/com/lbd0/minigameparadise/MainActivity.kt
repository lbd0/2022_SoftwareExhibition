package com.lbd0.minigameparadise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var initTime = 0L

    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var select :Button = findViewById(R.id.selectbnt)
        var start  : Button = findViewById(R.id.startbnt)


        select.setOnClickListener{
            var intent : Intent = Intent(this,CharacterBookActivity::class.java)
            startActivity(intent)
        }

        start.setOnClickListener{
            var intent : Intent = Intent(this, GameChoice::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {

        if(System.currentTimeMillis() - initTime > 3000) {
            Toast.makeText(this, "종료하려면 한 번 더 누르세요!!", Toast.LENGTH_SHORT).show()
            initTime = System.currentTimeMillis()
        }
        else
            super.onBackPressed()
    }
}