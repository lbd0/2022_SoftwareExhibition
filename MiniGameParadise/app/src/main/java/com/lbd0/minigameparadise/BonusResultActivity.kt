package com.lbd0.minigameparadise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lbd0.minigameparadise.databinding.ActivityBonusResultBinding

class BonusResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_bonus_result)
        val binding = ActivityBonusResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val num = getIntent().getIntExtra("bonus_result", -1)

        if(num == 1) {
            binding.bonusResult.setText("Final Score + 30")
        } else {
            binding.bonusResult.setText("꽝")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}