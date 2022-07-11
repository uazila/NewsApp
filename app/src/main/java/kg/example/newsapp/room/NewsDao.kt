package kg.example.newsapp.room

import androidx.room.*
import kg.models.News

@Dao
interface NewsDao {


    @Query("SELECT*FROM news")
    fun getall(): List<News>

    @Insert
    fun insert(news: News)

    @Update
    fun update(news: News)

    @Query("SELECT*FROM news ORDER BY createdAt Desc")
    fun sortAll(): List<News>


    @Delete
    fun delete(news: News)

    @Query("SELECT*FROM news WHERE title LIKE '%' || :search || '%'")
    fun getSearch(search: String?): List<News>
}
