package com.lbd0.minigameparadise

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.lbd0.minigameparadise.databinding.ActivityMoleBinding

private const val TAG_ON = "on"
private const val TAG_OFF = "off"

class MoleActivity : AppCompatActivity() {

    var score = 0;
    val thread : Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_mole)

        val binding = ActivityMoleBinding.inflate(layoutInflater)  // 뷰바인딩
        setContentView(binding.root)

        val txt_timer = binding.moleTimer           // 타이머 텍스트
        val txt_score = binding.moleScore           // 현재 점수 텍스트
        val txt_bestScore = binding.moleBestScore   // 최고 점수 텍스트
        val btn_start = binding.moleStart           // 시작 버튼

        val moles = arrayOf<ImageView>(binding.mole1, binding.mole2, binding.mole3, binding.mole4, binding.mole5, binding.mole6, binding.mole7, binding.mole8, binding.mole9)

        // 최고 점수 표시
        if(MoleResultActivity.spf == null) {
            txt_bestScore.setText("Best Score : $score")
        } else {
            txt_bestScore.setText("Best Score : ${MoleResultActivity.spf?.getInt("spfScore", 0)}")
        }

        for(i in 0 until moles.size) {
            moles[i].visibility = View.INVISIBLE
            moles[i].setTag(TAG_OFF)

            moles[i].setOnClickListener {
                if(it.getTag().toString().equals(TAG_ON)) {
                    Toast.makeText(applicationContext, "good", Toast.LENGTH_SHORT).show()
                    txt_score.setText("Score : ${score++}")
                    it.visibility = View.INVISIBLE
                    it.setTag(TAG_OFF)
                } else {
                    Toast.makeText(applicationContext, "bad", Toast.LENGTH_SHORT).show()
                    if(score <= 0) {
                        txt_score.setText("Score : $score")
                    }else {
                        txt_score.setText("Score : ${score--}")
                    }

                    it.visibility = View.VISIBLE
                    it.setTag(TAG_ON)
                }
            }
        }

        txt_timer.setText("Timer 30sec")

        btn_start.setOnClickListener {
            btn_start.visibility = View.GONE
            txt_score.visibility = View.VISIBLE


        }

    }
}
// 타이머랑 랜덤으로 두더지 올라오는거 구현해야함