package com.lbd0.minigameparadise

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.hardware.HardwareBuffer
import android.media.TimedText
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import kotlin.concurrent.timer

import java.util.*
import kotlin.concurrent.thread
import kotlin.math.log

class baseballGame : AppCompatActivity() {


    var num : Int =0
    var time : Int = 5
    var highnum : Int =0
    lateinit var timetxt:TextView
    lateinit var high:TextView
    lateinit var  score : TextView
    lateinit var plus: Button
    lateinit var start : Button
    var start1: Boolean=true

    var spf : SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.baseball)

        start = findViewById(R.id.start_btn)
        plus = findViewById(R.id.plus_btn)
        score = findViewById(R.id.now_txt)
        timetxt =findViewById(R.id.time_txt)
        high = findViewById(R.id.high_txt)

        spf = getSharedPreferences("ball", MODE_PRIVATE)


        plus.isEnabled=false;

        start.setOnClickListener{




            plus.isEnabled=true;
            start.isEnabled=false;

            thread(start1){
                Log.d("jun","aq")

                while(time>=0){
                    var mes=Message()

                    mes.arg1=time
                    haneler.sendMessage(mes)
                    time--
                    try {
                        Thread.sleep(1000)
                    } catch (e:InterruptedException) {
                        e.printStackTrace()
                    }
                }
                start1=false

                if(time<=0) {
                    Log.d("bada", "$time")
                    val msg = Message()
                    msg.arg1 = num
                    besthanedler.sendMessage(msg)


                    val mew = Message()
                    mew.arg1=1
                    endHandler.sendMessage(mew)

                    val mmm = Message()
                    mmm.arg1=1
                    restartHandler.sendMessage(mmm)
                }



            }
        }

        plus.setOnClickListener{
            ++num
            score.setText("Score : "+num)


        }

        intent = Intent(this, BonusActivity::class.java)
    }




    val endHandler : Handler= object  : Handler(){
        override fun handleMessage(msg: Message) {
            plus.isEnabled=false
        }
    }

    val haneler: Handler= object  : Handler(){
        override fun handleMessage(msg: Message) {
            timetxt.setText("Timer: "+msg.arg1+" sec")
        }
    }

    val besthanedler : Handler = object : Handler() {
        @SuppressLint("HandlerLeak")
        override fun handleMessage(msg: Message) {
                if(spf?.getInt("ball", 0)!! < msg.arg1) { // 최고 점수가 이번 점수보다 낮으면
                    spf?.edit()?.putInt("ball", msg.arg1)?.commit()  // 이번 점수를 최고 점수로 변경
                    high.setText("New Best: ${msg.arg1}")
                    Log.d("bada", "if ${spf?.getInt("ball", 0)}")
                    OneFragment.finalScore += 50
                    App.prefs.setInt("finalScore", OneFragment.finalScore)
                    startActivity(intent)
                } else {
                    high.setText("Best Score : ${spf?.getInt("ball", 0) ?: 0}")
                    Log.d("bada", "else ${spf?.getInt("ball", 0)}")
                }

        }
    }

    val restartHandler : Handler = object  : Handler(){
        override fun handleMessage(msg: Message) {
            num=0
            time=5
            timetxt.setText("Timer: "+5+" sec")
            score.setText("Score : 0")
            plus.isEnabled=false
            start.isEnabled=true
            start1=true
        }
    }

}






