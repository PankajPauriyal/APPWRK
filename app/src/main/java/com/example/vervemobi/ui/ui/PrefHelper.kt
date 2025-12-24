package com.example.vervemobi.ui.ui
import android.content.Context

class PrefHelper(context: Context) {

    private val prefs =
        context.getSharedPreferences("training_prefs", Context.MODE_PRIVATE)

    fun saveStatus(id: Int, completed: Boolean) {
        prefs.edit().putBoolean(id.toString(), completed).apply()
    }

    fun getStatus(id: Int): Boolean {
        return prefs.getBoolean(id.toString(), false)
    }
}
