package com.lbd0.minigameparadise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.lbd0.minigameparadise.databinding.ActivityCardBinding
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.thread

class CardActivity : AppCompatActivity() {
    lateinit var txt_timer : TextView
    val binding by lazy {
        ActivityCardBinding.inflate(layoutInflater)
    }
        val cardImg = mutableListOf(R.drawable.ani_rat, R.drawable.ani_cow, R.drawable.ani_tiger, R.drawable.ani_rabbit, R.drawable.ani_drag, R.drawable.ani_sna, R.drawable.ani_hor, R.drawable.ani_sheep, R.drawable.ani_mon, R.drawable.ani_chi, R.drawable.ani_dog, R.drawable.ani_pig)
        lateinit var card : MutableList<ImageView>

        var imgNum = 0
        val cardSet = mutableMapOf<ImageView, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            //setContentView(R.layout.activity_card)
            setContentView(binding.root)

            val btn_start = binding.cardStart
            val txt_score = binding.cardScore
            val txt_best = binding.cardBestScore
            txt_timer = binding.cardTimer
            card = mutableListOf<ImageView>(binding.card1, binding.card2, binding.card3, binding.card4, binding.card5, binding.card6, binding.card7, binding.card8)

            var score = 0

            txt_score.setText("Score : $score")
            txt_best.setText("Best Score : ${CardResultActivity.spf?.getInt("cardSpf", 0)?: 0}")

            for(i in 0 until card.size) {
                card[i].setImageResource(R.drawable.buriburimon)
            }

            Collections.shuffle(card)
            Collections.shuffle(cardImg)

            btn_start.setOnClickListener {
                btn_start.visibility = View.GONE
                var start:Boolean = true
                thread(start) {
                    val MAXTIME:Int = 30

                    for(i in MAXTIME downTo 0) {
                        val msg = Message()
                        msg.arg1 = i
                        handler.sendMessage(msg)

                        try {
                            Thread.sleep(1000)
                        } catch (e:InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                    start = false
                    val intent = Intent(this, CardResultActivity::class.java)
                    intent.putExtra("score", score)
                    startActivity(intent)
                    finish()
                }

                for(i in 0 until card.size) {
                    thread(start) {
                        try{
                            val msg1 = Message()
                            msg1.arg1 = i
                            cardOnHandler.sendMessage(msg1)
                            Thread.sleep(5000)

                            val msg2 = Message()
                            msg2.arg1 = i
                            cardOffHandler.sendMessage(msg2)

                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                }

                var cnt = 0
                for(i in 0 until card.size) {
                    card[i].setOnClickListener {
                        thread(start) {
                            try{
                                val msg3 = Message()
                                msg3.arg1 = i
                                cardOpenHandler.sendMessage(msg3)

                                var card1 = 0
                                if(cnt==0) {
                                    card1  = cardSet.get(card[msg3.arg1])!!
                                }
                                else {
                                    if(card1 == cardSet.get(card[msg3.arg1])!!)
                                }



                            } catch (e: InterruptedException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }

        }
        val handler:Handler = object :Handler() {
            override fun handleMessage(msg: Message) {
                txt_timer.setText("Timer : ${msg.arg1}")
            }
        }

        val cardOnHandler : Handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                if(imgNum >= card.size/2) imgNum = 0
                cardSet.set(card[msg.arg1], imgNum)
                card[msg.arg1].setImageResource(cardImg[imgNum++])
            }
        }

        val cardOffHandler: Handler = object :Handler() {
            override fun handleMessage(msg: Message) {
                card[msg.arg1].setImageResource(R.drawable.buriburimon)
            }
        }

        val cardOpenHandler:Handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                card[msg.arg1].setImageResource(cardImg[cardSet.get(card[msg.arg1])!!])
            }
        }

        val cardCloseHandler:Handler = object:Handler() {
            override fun handleMessage(msg: Message) {
                card[msg.arg1].setImageResource(R.drawable.buriburimon)
            }
        }
}