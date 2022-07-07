package kg.example.newsapp.ui.notifications

import android.content.Context

class Prefs(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun saveState() {
        preferences.edit().putBoolean("isShow", true).apply()
    }

    fun isShow(): Boolean {
        return preferences.getBoolean("isShow", false)
    }

    fun saveName(name: String) {
        preferences.edit().putString("save", name).apply()
    }

    fun getName(): String? {
        return preferences.getString("save", "")
    }
}