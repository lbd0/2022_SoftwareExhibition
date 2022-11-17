package com.lbd0.minigameparadise

import java.util.Random

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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

    var spf : SharedPreferences? = null




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

        spf = getSharedPreferences("up", MODE_PRIVATE)


        var life: TextView = findViewById(R.id.lifecnt)


        Log.d("ggsd","${spf}")

        hiText.setText("Best Score : ${spf?.getInt("up", 0) ?: 0}")





        checkButton.setOnClickListener {

            var user: String = editText.text.toString() //정답 비교
            var userInt: Int = user.toInt()
            if (userInt == ranNum) {
                result.setText("result : 정답")

                scoreText.setText("score : "+score)
                ispass=true

                checkButton.isEnabled=false


                if(spf?.getInt("up", 0)!! < score) { // 최고 점수가 이번 점수보다 낮으면
                    spf?.edit()?.putInt("up",score)?.commit()  // 이번 점수를 최고 점수로 변경
                    hiText.setText("New Best: ${score}")

                } else {
                    hiText.setText("Best Score : ${spf?.getInt("up", 0) ?: 0}")
                }



            }
            else if(userInt > ranNum){
                result.setText("result : DOWN")
            }

            else if(userInt < ranNum){
                result.setText("result : UP")
            }

            else{
                result.setText("result : 오답")
            }

            lifec=lifec-1

            lifecnt.setText("life : "+lifec)

            if(lifec<=0){
                checkButton.isEnabled=false
                result.setText("result : 실패")
            }


            if(ispass==true){
                lifec=7
                lifecnt.setText("life : "+lifec)
                result.setText("result : ")
                checkButton.isEnabled=true
                ispass=false
            }





        }

        resetButton.setOnClickListener {
            lifec=7
            lifecnt.setText("life : "+lifec)
            result.setText("result : ")
            checkButton.isEnabled=true


        }

    }

    fun makeNum() : Int {
        val random = Random()
        var r= random.nextInt(100)

        return r
    }

}
