package com.example.mohamedashour.weatherapp.data.db.daos

import androidx.room.*
import com.example.mohamedashour.weatherapp.data.db.entities.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll() : List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getMovieById(id: String) : Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movie: Movie)

    @Delete
    fun delete(movie: Movie)
}