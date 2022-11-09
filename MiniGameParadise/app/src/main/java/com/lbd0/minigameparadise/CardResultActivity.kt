package com.lbd0.minigameparadise

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lbd0.minigameparadise.databinding.ActivityCardResultBinding
import com.lbd0.minigameparadise.databinding.ActivityMoleResultBinding

class CardResultActivity : AppCompatActivity() {
    companion object {
        var spf : SharedPreferences? = null;    // 최고 점수 저장
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_mole_result)

        val binding = ActivityCardResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spf = getSharedPreferences("spfScore", MODE_PRIVATE)    // 키 값이 또 있으면 덮어씀

        val result = binding.cardResult
        val retry = binding.cardRetrybtn
        val toMain = binding.cardTomainbtn

        val score = getIntent().getIntExtra("score", -1)    // 점수


        if(spf?.getInt("spfScore", 0)!! < score) { // 최고 점수가 이번 점수보다 낮으면
            spf?.edit()!!.putInt("spfScore", score).commit()  // 이번 점수를 최고 점수로 변경
            result.setText("New Best\n$score")
        } else {
            result.setText("Best Score\n${spf?.getInt("spfScore", 0) ?: 0}")
        }

        retry.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            startActivity(intent)
            finish()
        }

        toMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}