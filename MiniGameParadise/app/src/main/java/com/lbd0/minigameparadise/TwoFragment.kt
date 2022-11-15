package com.lbd0.minigameparadise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.lbd0.minigameparadise.databinding.FragmentOneBinding
import com.lbd0.minigameparadise.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTwoBinding.inflate(inflater, container, false)
        var maxIndex = 0
        val img_view = mutableListOf<ImageView>(binding.cha1, binding.cha2, binding.cha3, binding.cha4, binding.cha5, binding.cha6)
        val img_res = mutableListOf<Int>(R.drawable.ani_hor2, R.drawable.ani_sheep2, R.drawable.ani_mon2, R.drawable.ani_chi2, R.drawable.ani_dog2, R.drawable.ani_pig2)

        binding.finalScore.setText("Final Score : ${OneFragment.finalScore}")

        when(OneFragment.finalScore) {
            in 601..700 -> {
                maxIndex = 0
            }
            in 701..800 -> {
                maxIndex = 1
            }
            in 801..900 -> {
                maxIndex = 2
            }
            in 901..1000 -> {
                maxIndex = 3
            }
            in 1001..1100 -> {
                maxIndex = 4
            }
            in 1101..1200 -> {
                maxIndex = 5
            }
            else -> maxIndex = 5
        }

        if(OneFragment.finalScore > 600) {
            for (i in 0..maxIndex) {
                img_view[i].setImageResource(img_res[i])
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}