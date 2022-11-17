package com.lbd0.minigameparadise

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.internal.Ref
import kotlin.random.Random

class GameChoice : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gamechoice)

        var moleBtn :Button = findViewById(R.id.molebtn)
        var cardBtn :Button = findViewById(R.id.cardbtn)
        var baseBtn : Button= findViewById(R.id.baseballbtn)
        var updownBtn : Button= findViewById(R.id.updownbtn)
        var randomBtn : Button = findViewById(R.id.randombtn)

        moleBtn.setOnClickListener{
             var intent : Intent=Intent(this,MoleActivity::class.java)
             startActivity(intent)
            finish()
        }

        cardBtn.setOnClickListener{
            var intent : Intent= Intent(this,CardActivity::class.java)
            startActivity(intent)
            finish()
        }
        baseBtn.setOnClickListener{
            var intent: Intent = Intent (this, baseballGame::class.java)
            startActivity(intent)
        }
        updownBtn.setOnClickListener{
            var intent : Intent = Intent(this, updownGame::class.java)
            startActivity(intent)
        }
        randomBtn.setOnClickListener{
            val random = Random

            val gnum = random.nextInt(5) + 1
            lateinit var intent: Intent

            when (gnum) {
                1 -> intent = Intent(this, MoleActivity::class.java)
                2 -> intent = Intent(this, CardActivity::class.java)
                3 -> intent = Intent(this, baseballGame::class.java)
                4 -> intent = Intent(this, updownGame::class.java)
                else -> Log.d("bada", "$gnum")
            }
            startActivity(intent)
            finish()
        }








    }
}