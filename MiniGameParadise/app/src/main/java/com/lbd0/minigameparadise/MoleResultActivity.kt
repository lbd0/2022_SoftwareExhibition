package com.lbd0.minigameparadise

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lbd0.minigameparadise.databinding.ActivityMoleResultBinding

class MoleResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_mole_result)

        val binding = ActivityMoleResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = binding.moleResult
        val retry = binding.moleRetrybtn
        val toMain = binding.moleTomainbtn

        val score = getIntent().getIntExtra("mole_score", -1)    // 점수

        if(App.prefs.getInt("spfScore", 0) < score) { // 최고 점수가 이번 점수보다 낮으면
            App.prefs.setInt("spfScore", score) // 이번 점수를 최고 점수로 변경
            result.setText("New Best\n$score")
            OneFragment.finalScore += 50
        } else {
            result.setText("Best Score\n${App.prefs.getInt("spfScore", 0)}")
        }

        retry.setOnClickListener {
            val intent = Intent(this, MoleActivity::class.java)
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