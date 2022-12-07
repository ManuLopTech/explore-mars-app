package mx.com.exploremars

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class App: Application() {
    companion object {
        lateinit var appContext: Context
        lateinit var executors: Executors
        lateinit var preferences: SharedPreferences
    }

    override fun onCreate() {
        try {
            appContext = applicationContext
            executors = Executors()
            preferences = getSharedPreferences("SysConfig", MODE_PRIVATE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onCreate()
    }
}