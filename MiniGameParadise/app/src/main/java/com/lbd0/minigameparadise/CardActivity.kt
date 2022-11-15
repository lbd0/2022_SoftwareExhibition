package com.lbd0.minigameparadise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils.TruncateAt
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.lbd0.minigameparadise.databinding.ActivityCardBinding
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.thread

private const val TAG_OPEN = "open"
private const val TAG_CORRECT = "correct"
private const val TAG_WRONG = "wrong"

class CardActivity : AppCompatActivity() {
    lateinit var txt_timer: TextView
    val binding by lazy {
        ActivityCardBinding.inflate(layoutInflater)
    }
    val cardImg = mutableListOf(
        R.drawable.ani_rat,
        R.drawable.ani_cow,
        R.drawable.ani_tiger,
        R.drawable.ani_rabbit,
        R.drawable.ani_drag,
        R.drawable.ani_sna,
        R.drawable.ani_hor,
        R.drawable.ani_sheep,
        R.drawable.ani_mon,
        R.drawable.ani_chi,
        R.drawable.ani_dog,
        R.drawable.ani_pig
    )
    lateinit var card: MutableList<ImageView>
    val txt_score by lazy {
        binding.cardScore
    }
    var score = 0
    var imgNum = 0
    val cardSet = mutableMapOf<ImageView, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_card)
        setContentView(binding.root)

        val btn_start = binding.cardStart
        val txt_best = binding.cardBestScore
        txt_timer = binding.cardTimer
        card = mutableListOf<ImageView>(
            binding.card1,
            binding.card2,
            binding.card3,
            binding.card4,
            binding.card5,
            binding.card6,
            binding.card7,
            binding.card8,
            binding.card9,
            binding.card10,
            binding.card11,
            binding.card12
        )

        var cnt = 0
        var card1 = 0
        var isOpen = false
        var start = true
        var finish = false

        txt_score.setText("Score : $score")
        txt_best.setText("Best Score : ${App.prefs.getInt("cardSpf", 0)}")

        for (i in 0 until card.size) {
            card[i].setImageResource(R.drawable.buriburimon)
            card[i].setTag(TAG_WRONG)
        }

        Collections.shuffle(card)
        Collections.shuffle(cardImg)

        for (i in 0 until card.size) {
            card[i].setOnClickListener {
                if (card[i].getTag().equals(TAG_WRONG)) {
                    thread(start) {
                        try {
                            if (cnt < 2) {
                                val msg3 = Message()
                                msg3.arg1 = i
                                cardOpenHandler.sendMessage(msg3)
                                if (cnt == 0) {
                                    card1 = i
                                    cnt++
                                    card[i].setTag(TAG_OPEN)
                                } else if (cnt < 2) {
                                    cnt++
                                    isOpen = true
                                    if (cardSet.get(card[card1])!! == cardSet.get(card[msg3.arg1])!!) {

                                        card[i].setTag(TAG_CORRECT)
                                        card[card1].setTag(TAG_CORRECT)

                                        score++
                                        val msg6 = Message()
                                        msg6.arg1 = score
                                        txtHandler.sendMessage(msg6)

                                        if (score >= 6) finish = true
                                        Thread.sleep(100)
                                    } else {
                                        Thread.sleep(1000)
                                        val msg4 = Message()
                                        msg4.arg1 = i
                                        cardOffHandler.sendMessage(msg4)

                                        val msg5 = Message()
                                        msg5.arg1 = card1
                                        cardOffHandler.sendMessage(msg5)
                                        card[card1].setTag(TAG_WRONG)
                                    }

                                    if (isOpen) {
                                        isOpen = false
                                        cnt = 0
                                    }
                                }
                            }
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        btn_start.setOnClickListener {
            btn_start.visibility = View.GONE

            thread(start) {
                val MAXTIME: Int = 20

                for (i in MAXTIME downTo 0) {
                    val msg = Message()
                    msg.arg1 = i
                    handler.sendMessage(msg)

                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    if (finish) {
                        score += i
                        val msg7 = Message()
                        msg7.arg1 = score
                        txtHandler.sendMessage(msg7)
                        break
                    };
                }
                start = false
                val intent = Intent(this, CardResultActivity::class.java)
                intent.putExtra("card_score", score)
                startActivity(intent)
                finish()
            }

            for (i in 0 until card.size) {
                thread(start) {
                    try {
                        val msg1 = Message()
                        msg1.arg1 = i
                        cardOnHandler.sendMessage(msg1)
                        Thread.sleep(500)

                        val msg2 = Message()
                        msg2.arg1 = i
                        cardOffHandler.sendMessage(msg2)

                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            txt_timer.setText("Timer : ${msg.arg1}")
        }
    }

    val cardOnHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (imgNum >= card.size / 2) imgNum = 0
            cardSet.set(card[msg.arg1], imgNum)
            card[msg.arg1].setImageResource(cardImg[imgNum++])
        }
    }

    val cardOffHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            card[msg.arg1].setImageResource(R.drawable.buriburimon)
        }
    }

    val cardOpenHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            card[msg.arg1].setImageResource(cardImg[cardSet.get(card[msg.arg1])!!])
        }
    }

    val txtHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            txt_score.setText("카드 수 : $score")
        }
    }
}