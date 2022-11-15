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
        val img_res = mutableListOf<Int>(R.drawable.ani_rat2, R.drawable.ani_cow2, R.drawable.ani_tiger2, R.drawable.ani_rabbit2, R.drawable.ani_drag2, R.drawable.ani_sna2)
        var maxIndex = 0;
        binding.finalScore.setText("Final Score : $finalScore")

        when(finalScore) {
            in 0..100 -> {
                maxIndex = 0
            }
            in 101..200 -> {
                maxIndex = 1
            }
            in 201..300 -> {
                maxIndex = 2
            }
            in 301..400 -> {
                maxIndex = 3
            }
            in 401..500 -> {
                maxIndex = 4
            }
            in 501..600 -> {
                maxIndex = 5
            }
            else -> maxIndex = 5
        }

        for(i in 0 ..maxIndex) {
            img_view[i].setImageResource(img_res[i])
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}