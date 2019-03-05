package com.vodrex.mdb.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ensar.tmdbkotlin.db.GenreTypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity()
data class Trailer(
        @PrimaryKey()
        @SerializedName("id")
        @Expose
        var id: String?,

        @SerializedName("key")
        @Expose
        var key: String?,

        @SerializedName("genre_ids")
        @Expose
        @TypeConverters(GenreTypeConverters::class)
        var genreIds: List<Int>?,

        @Expose(serialize = false)
        var genres: String?,

        @SerializedName("site")
        @Expose
        var site: String?,

        @SerializedName("name")
        @Expose
        var name: String?)