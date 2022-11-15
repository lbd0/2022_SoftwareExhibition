package com.lbd0.minigameparadise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.lbd0.minigameparadise.databinding.ActivityCharacterBookBinding

class CharacterBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_character_book)
        val binding = ActivityCharacterBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.tabContent, OneFragment()).commit()

        val tabLayout = binding.tabs

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                when(tab?.text) {
                    "Page1" -> transaction.replace(R.id.tabContent, OneFragment())
                    "Page2" -> transaction.replace(R.id.tabContent, TwoFragment())
                }
                transaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("bada", "onTabUnselected...")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("bada", "onTabReselected...")
            }
        })
    }
}