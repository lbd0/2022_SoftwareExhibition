package com.lbd0.minigameparadise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.internal.Ref

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
        }

        cardBtn.setOnClickListener{
            var intent : Intent= Intent(this,cardGame::class.java)
            startActivity(intent)
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
            var intent : Intent = Intent(this, randomGame::class.java)
            startActivity(intent)
        }








    }
}