package com.lbd0.minigameparadise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lbd0.minigameparadise.databinding.ActivityBonusBinding
import java.util.*

class BonusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_bonus)
        val binding = ActivityBonusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val g_btn = binding.bonusGreen
        val r_btn = binding.bonusRed
        var num = 0
        val list =listOf<String>("over", "bonus")

        Collections.shuffle(list)

        g_btn.setOnClickListener {
            g_btn.setTag(list[0])
            if(g_btn.getTag().equals("bonus")) {
                OneFragment.finalScore += 30
                App.prefs.setInt("finalScore", OneFragment.finalScore)
                num = 1
            } else {
                num = 0
            }

            val intent = Intent(this, BonusResultActivity::class.java)
            intent.putExtra("bonus_result", num)
            startActivity(intent)
            finish()
        }

        r_btn.setOnClickListener {
            r_btn.setTag(list[1])
            if(r_btn.getTag().equals("bonus")) {
                OneFragment.finalScore += 30
                App.prefs.setInt("finalScore", OneFragment.finalScore)
                num = 1
            } else {
                num = 0
            }

            val intent = Intent(this, BonusResultActivity::class.java)
            intent.putExtra("bonus_result", num)
            startActivity(intent)
            finish()
        }
    }
}