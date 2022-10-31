package com.lbd0.minigameparadise

import java.util.Random

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class updownGame : AppCompatActivity() {

    var ranNum: Int = makeNum();
    var lifec: Int=5;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.updown)

        var checkButton: Button = findViewById(R.id.c_btn)
        var resetButton: Button = findViewById(R.id.r_btn)
        var editText: EditText = findViewById(R.id.input_text)
        var result: TextView = findViewById(R.id.result_txt)
        var lifecnt : TextView = findViewById(R.id.lifecnt)


        var life: TextView = findViewById(R.id.life_txt)
//        var ranNum: Int? = null


        checkButton.setOnClickListener {

            var user: String = editText.text.toString() //정답 비교
            var userInt: Int = user.toInt()
            if (userInt == ranNum) {
                result.setText("정답")
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


        }


        resetButton.setOnClickListener {
            lifec=5
            lifecnt.setText(""+lifec)
            result.setText("")
            checkButton.isEnabled=true




        }


    }


    fun makeNum() : Int {
        val random = Random()
        var r= random.nextInt(100)

        return r


    }




    fun compare() {  //랜덤 숫자와 사용자 입력 숫자를 비교
//
//        var user: String = editText.text.toString()
//
//        var userInt: Int = user.toInt()
//
//        if (userInt == ranNum) {
//            result.setText("정답")
//
//        }
//
//
//    }
//
    }
}
