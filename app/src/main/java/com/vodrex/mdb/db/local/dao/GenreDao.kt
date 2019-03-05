package com.vodrex.mdb.db.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vodrex.mdb.db.entities.Genre
import io.reactivex.Single

@Dao
interface GenreDao {

    @Query("SELECT * FROM Genre")
    fun getGenres(): Single<List<Genre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(example: List<Genre>)
}