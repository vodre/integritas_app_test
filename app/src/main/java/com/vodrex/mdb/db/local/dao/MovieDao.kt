package com.vodrex.mdb.db.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vodrex.mdb.db.entities.Movie
import com.vodrex.mdb.utils.MovieListType
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie WHERE listType = :listType ORDER BY orderId")
    fun getMovies(listType: String): Single<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getMovie(id: Long): Single<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(example: List<Movie>)
}