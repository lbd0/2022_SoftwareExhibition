package com.lbd0.minigameparadise

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lbd0.minigameparadise.databinding.ActivityCardResultBinding
import com.lbd0.minigameparadise.databinding.ActivityMoleResultBinding

class CardResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_mole_result)

        val binding = ActivityCardResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = binding.cardResult
        val retry = binding.cardRetrybtn
        val toMain = binding.cardTomainbtn
        val txt_score = binding.cardFinal

        val score = getIntent().getIntExtra("card_score", -1)    // 점수

        txt_score.setText("Score : $score")


        if(App.prefs.getInt("cardSpf", 0)!! < score) { // 최고 점수가 이번 점수보다 낮으면
            App.prefs.setInt("cardSpf", score)  // 이번 점수를 최고 점수로 변경
            result.setText("New Best\n$score")
            OneFragment.finalScore += 50
            App.prefs.setInt("finalScore", OneFragment.finalScore)

            val intent = Intent(this, BonusActivity::class.java)
            startActivity(intent)
        } else {
            result.setText("Best Score\n${App.prefs.getInt("cardSpf", 0) ?: 0}")
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