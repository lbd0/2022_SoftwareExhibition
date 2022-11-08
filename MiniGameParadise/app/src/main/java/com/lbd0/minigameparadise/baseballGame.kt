package com.lbd0.minigameparadise

import android.media.TimedText
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import kotlin.concurrent.timer

import java.util.*
import kotlin.concurrent.thread

class baseballGame : AppCompatActivity() {


    var num : Int =0
    var time : Int = 5
    var highnum : Int =0
    lateinit var timetxt:TextView
    lateinit var high:TextView
    lateinit var  score : TextView
    lateinit var plus: Button
    lateinit var start : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.baseball)

        start = findViewById(R.id.start_btn)
        plus = findViewById(R.id.plus_btn)
        score = findViewById(R.id.now_txt)
        timetxt =findViewById(R.id.time_txt)
        high = findViewById(R.id.high_txt)


        plus.isEnabled=false;



        start.setOnClickListener{

            plus.isEnabled=true;
            start.isEnabled=false;
            var start: Boolean=true

            thread(start){

                while(time>=0){

                    if(time==0){
                        start=false
                    }
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

            }

        }

        plus.setOnClickListener{
            ++num
            score.setText(""+num)

            if(time<0){
                plus.isEnabled=false;

                compare(num)


                high.setText(""+highnum)
                reset()
            }


        }


    }

    val haneler: Handler= object  : Handler(){
        override fun handleMessage(msg: Message) {
            timetxt.setText(""+msg.arg1)
        }
    }


    fun reset(){
        num=0
        time=5
        timetxt.setText(""+5)
        score.setText(""+num)
        plus.isEnabled=false

    }

    fun compare(h:Int):Int {

        if(highnum<h){
            highnum=h
        }

        return highnum

    }


}



