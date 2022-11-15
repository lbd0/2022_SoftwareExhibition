package com.lbd0.minigameparadise

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context : Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getInt(key: String, defValue: Int) : Int {
        return prefs.getInt(key, defValue)
    }

    fun setInt(key: String, defValue: Int) {
        prefs.edit().putInt(key, defValue).apply()
    }
}