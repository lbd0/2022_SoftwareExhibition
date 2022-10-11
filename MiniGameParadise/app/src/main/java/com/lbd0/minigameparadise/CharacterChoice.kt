package com.lbd0.minigameparadise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CharacterChoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_choice)

        var finalScore = 0 // 파이널 점수 => 일정 점수 이상이면 캐릭터 오픈
        var index = 0   // img_view 배열 인덱스로 사용할 것

        var character : ImageView = findViewById(R.id.presentcharac) // 현재 캐릭터 이미지뷰
        val choice : Button =  findViewById<Button>(R.id.choicebtn) // 선택! 버튼

        // 이미지뷰를 담은 배열
        val img_view : Array<ImageView> = Array(12, {findViewById(R.id.ani_rat)})
        // 이미지뷰에 넣을 이미지 아이디 배열
        val img_ID = intArrayOf(R.id.ani_rat, R.id.ani_cow, R.id.ani_tiger, R.id.ani_rabbit, R.id.ani_drag, R.id.ani_sna, R.id.ani_hor, R.id.ani_sheep, R.id.ani_mon, R.id.ani_chi, R.id.ani_dog, R.id.ani_pig)
        // 잠금해제 된 이미지 리소스
        val img_res = intArrayOf(R.drawable.ani_rat2, R.drawable.ani_cow2, R.drawable.ani_tiger2, R.drawable.ani_rabbit2, R.drawable.ani_drag2, R.drawable.ani_sna2, R.drawable.ani_hor2, R.drawable.ani_sheep2, R.drawable.ani_mon2, R.drawable.ani_chi2, R.drawable.ani_dog2, R.drawable.ani_pig2)
        // 현재 캐릭터 이미지뷰에 들어갈 이미지 리소스
        val cha_res = intArrayOf(R.drawable.ani_rat, R.drawable.ani_cow, R.drawable.ani_tiger, R.drawable.ani_rabbit, R.drawable.ani_drag, R.drawable.ani_sna, R.drawable.ani_hor, R.drawable.ani_sheep, R.drawable.ani_mon, R.drawable.ani_chi, R.drawable.ani_dog, R.drawable.ani_pig)

        // 반복문으로 이미지뷰 제어
        for(i in 0 until 12) {
            img_view[i] = findViewById<ImageView>(img_ID[i]) // 각 이미지뷰에 아이디 넣어주기

            img_view[i].setOnClickListener { // 각 이미지뷰 클릭이벤트
                if(img_view[i].getTag() == "unlock")
                    character.setImageResource(cha_res[i])  // 클릭한 이미지로 현재 캐릭터 이미지뷰 리소스 변경
            }
        }

        // finalScore가 일정 점수 이상이면 캐릭터 오픈
        when(finalScore){
            in 0..100 -> {
                img_view[index].setImageResource(img_res[index])    // 캐릭터 오픈 이미지로 변경
                img_view[index++].setTag("unlock")                  // 태그 unlock으로 설정
            }
            in 101..200 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            in 201..300 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            in 301..400 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            in 401..500 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            in 501..600 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            in 601..700 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            in 701..800 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            in 801..900 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            in 901..1000 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            in 1001..1100 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            in 1101..1200 -> {
                img_view[index].setImageResource(img_res[index])
                img_view[index++].setTag("unlock")
            }
            else -> img_view[index].setTag("unlock")
        }


        // 선택 버튼 이벤트
        choice.setOnClickListener { // 선택 버튼 누르면 시작 화면으로 전환
            var intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}