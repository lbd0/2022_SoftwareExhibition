package com.lbd0.minigameparadise

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.lbd0.minigameparadise.databinding.ActivityMoleBinding
import kotlinx.coroutines.Runnable
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.thread


private const val TAG_MOLE = "mole"
private const val TAG_BU = "buriburimon"
private const val TAG_OFF = "off"

class MoleActivity : AppCompatActivity() {

    var score = 0;

    lateinit var moles : Array<ImageView>
    val moleRes = arrayOf(R.drawable.mole_rat, R.drawable.mole_cow, R.drawable.mole_tiger, R.drawable.mole_rab, R.drawable.mole_drag, R.drawable.mole_sna, R.drawable.mole_hor, R.drawable.mole_sheep, R.drawable.mole_mon, R.drawable.mole_chi, R.drawable.mole_dog, R.drawable.mole_pig, R.drawable.buriburimon)
    lateinit var txt_timer : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_mole)
        val binding = ActivityMoleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        moles = arrayOf<ImageView>(binding.mole1, binding.mole2, binding.mole3, binding.mole4, binding.mole5, binding.mole6, binding.mole7, binding.mole8, binding.mole9)
        val txt_score = binding.moleScore           // 현재 점수 텍스트
        val txt_bestScore = binding.moleBestScore   // 최고 점수 텍스트
        val btn_start = binding.moleStart           // 시작 버튼
        txt_timer = binding.moleTimer        // 타이머 텍스트


        // 최고 점수 표시
        txt_bestScore.setText("Best Score : ${App.prefs.getInt("spfScore", 0)}")

        for(i in 0 until moles.size) {
            moles[i].visibility = View.INVISIBLE
            moles[i].setOnClickListener {
                if(it.getTag().toString().equals(TAG_MOLE)) {
                    txt_score.setText("Score : ${++score}")
                    it.visibility = View.INVISIBLE
                } else if(it.getTag().toString().equals(TAG_BU)){
                    if(score <= 0) {
                        txt_score.setText("Score : $score")
                    }else {
                        txt_score.setText("Score : ${--score}")
                    }
                    it.visibility = View.INVISIBLE
                } else {
                    val toast = Toast.makeText(applicationContext, "late!", Toast.LENGTH_SHORT)
                    toast.show()
                    Thread.sleep(500)
                    toast.cancel()
                }
            }
        }

        txt_timer.setText("Timer : 30 sec")

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
                val intent = Intent(this, MoleResultActivity::class.java)
                intent.putExtra("mole_score", score)
                startActivity(intent)
                finish()
            }

            for(i in 0 until moles.size) {
                thread(start) {
                    while(true) {
                        try {
                            val msg1 = Message()
                            val offtime = Random().nextInt(5000) + 500L
                            Thread.sleep(offtime)


                            msg1.arg1 = i
                            onHandler.sendMessage(msg1)

                            val msg2 = Message()
                            val ontime = Random().nextInt(1000) + 500L
                            Thread.sleep(ontime)

                            msg2.arg1 = i
                            offHandler.sendMessage(msg2)

                        } catch (e : InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

    }

    @SuppressLint("HandlerLeak")
    val onHandler = object : Handler() {
        override fun handleMessage(msg : Message) {
            moles[msg.arg1].visibility = View.VISIBLE
            val mole = Random().nextInt(moleRes.size)
            moles[msg.arg1].setImageResource(moleRes[mole])
            if(mole == 12) moles[msg.arg1].setTag(TAG_BU)
            else moles[msg.arg1].setTag(TAG_MOLE)
        }
    }

    val offHandler = object:Handler() {
        override fun handleMessage(msg: Message) {
            moles[msg.arg1].visibility = View.INVISIBLE
            moles[msg.arg1].setTag(TAG_OFF)
        }
    }


    val handler = object:Handler() {
        override fun handleMessage(msg: Message) {
            txt_timer.setText("Timer : ${msg.arg1} sec")
        }
    }

}
