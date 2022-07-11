package kg.example.newsapp.ui.notifications

import android.app.Application
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import kg.example.newsapp.room.AppDataBase

class App : Application() {
       companion object {
           lateinit var database: AppDataBase
       }
    override fun onCreate() {
        super.onCreate()
        database= databaseBuilder(this, AppDataBase::class.java, "database" )
            .allowMainThreadQueries()
            .build()
    }
}