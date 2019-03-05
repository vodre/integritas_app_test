package com.vodrex.mdb.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ensar.tmdbkotlin.db.GenreTypeConverters
import com.vodrex.mdb.db.entities.Genre
import com.vodrex.mdb.db.entities.Movie
import com.vodrex.mdb.db.local.dao.GenreDao
import com.vodrex.mdb.db.local.dao.MovieDao

@Database(entities = [Movie::class, Genre::class], version = 1, exportSchema = false)
@TypeConverters(GenreTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
}