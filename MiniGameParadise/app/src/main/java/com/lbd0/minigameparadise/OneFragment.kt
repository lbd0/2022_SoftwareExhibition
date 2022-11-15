package com.lbd0.minigameparadise

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.lbd0.minigameparadise.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    companion object {
        var finalScore = App.prefs.getInt("finalScore", 0)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        val img_view = mutableListOf<ImageView>(binding.cha1, binding.cha2, binding.cha3, binding.cha4, binding.cha5, binding.cha6)
        val img_res = mutableListOf<Int>(R.drawable.ani_lock_chi, R.drawable.ani_lock_cow, R.drawable.ani_lock_tiger, R.drawable.ani_lock_rabbit, R.drawable.ani_lock_drag, R.drawable.ani_lock_sna)

        binding.finalScore.setText("Final Score : $finalScore")

        for(i in 0 until img_view.size) {
            img_view[i].setImageResource(img_res[i])
        }

        when(finalScore) {
            in 0..100 -> {
                binding.cha1.setImageResource(R.drawable.ani_rat2)
            }
            in 101..200 -> {
                binding.cha2.setImageResource(R.drawable.ani_cow2)
            }
            in 201..300 -> {
                binding.cha3.setImageResource(R.drawable.ani_tiger2)
            }
            in 301..400 -> {
                binding.cha4.setImageResource(R.drawable.ani_rabbit2)
            }
            in 401..500 -> {
                binding.cha5.setImageResource(R.drawable.ani_drag2)
            }
            in 501..600 -> {
                binding.cha6.setImageResource(R.drawable.ani_sna2)
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}