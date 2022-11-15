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

        val img_view = mutableListOf<ImageView>(binding.cha1, binding.cha2, binding.cha3, binding.cha4, binding.cha5, binding.cha6)
        val img_res = mutableListOf<Int>(R.drawable.ani_lock_hor, R.drawable.ani_lock_sheep, R.drawable.ani_lock_mon, R.drawable.ani_lock_chi, R.drawable.ani_lock_dog, R.drawable.ani_lock_pig)

        binding.finalScore.setText("Final Score : ${OneFragment.finalScore}")

        for(i in 0 until img_view.size) {
            img_view[i].setImageResource(img_res[i])
        }

        when(OneFragment.finalScore) {
            in 601..700 -> {
                binding.cha1.setImageResource(R.drawable.ani_hor2)
            }
            in 701..800 -> {
                binding.cha2.setImageResource(R.drawable.ani_sheep2)
            }
            in 801..900 -> {
                binding.cha3.setImageResource(R.drawable.ani_mon2)
            }
            in 901..1000 -> {
                binding.cha4.setImageResource(R.drawable.ani_chi2)
            }
            in 1001..1100 -> {
                binding.cha5.setImageResource(R.drawable.ani_dog2)
            }
            in 1101..1200 -> {
                binding.cha6.setImageResource(R.drawable.ani_pig2)
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}