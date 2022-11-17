package com.lbd0.minigameparadise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.lbd0.minigameparadise.databinding.ActivityCharacterBookBinding

class CharacterBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_character_book)
        val binding = ActivityCharacterBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = MyFragmentPagerViewAdapter(this)
    }
}
class MyFragmentPagerViewAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    val fragments : List<Fragment>
    init {
        fragments =  listOf(OneFragment(), TwoFragment())
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
