package kg.example.newsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.models.News


@Database(entities = [News:: class], version = 1)
abstract class  AppDataBase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}