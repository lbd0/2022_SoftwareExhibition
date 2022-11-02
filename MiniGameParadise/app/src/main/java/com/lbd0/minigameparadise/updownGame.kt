package com.lbd0.minigameparadise

import java.util.Random

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class updownGame : AppCompatActivity() {

    var ranNum: Int = makeNum();
    var lifec: Int=7;
    var highscore:Int=0
    var score: Int = 0
    var ispass :Boolean=false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.updown)

        var checkButton: Button = findViewById(R.id.c_btn)
        var resetButton: Button = findViewById(R.id.r_btn)
        var editText: EditText = findViewById(R.id.input_text)
        var result: TextView = findViewById(R.id.result_txt)
        var lifecnt : TextView = findViewById(R.id.lifecnt)
        var scoreText : TextView = findViewById(R.id.scor_txt)
        var hiText : TextView = findViewById(R.id.highScore_txt)


        var life: TextView = findViewById(R.id.life_txt)
//        var ranNum: Int? = null


        checkButton.setOnClickListener {

            var user: String = editText.text.toString() //정답 비교
            var userInt: Int = user.toInt()
            if (userInt == ranNum) {
                result.setText("정답")
                ++score
                scoreText.setText(""+score)
                ispass=true

            }
            else if(userInt > ranNum){
                result.setText("DOWN")
            }

            else if(userInt < ranNum){
                result.setText("UP")
            }

            else{
                result.setText("오답")
            }

            lifec=lifec-1

            lifecnt.setText(""+lifec)

            if(lifec<=0){
                checkButton.isEnabled=false
                result.setText("실패")
            }


            if(ispass==true){
                lifec=7
                lifecnt.setText(""+lifec)
                result.setText("")
                checkButton.isEnabled=true
                ispass=false
            }


            if(highscore<score){
                highscore=score
                hiText.setText(""+highscore)

            }


        }


        resetButton.setOnClickListener {
            lifec=5
            lifecnt.setText(""+lifec)
            result.setText("")
            checkButton.isEnabled=true


        }


//        hiText.setText(highscore)













    }


    fun makeNum() : Int {
        val random = Random()
        var r= random.nextInt(100)

        return r
    }





}
