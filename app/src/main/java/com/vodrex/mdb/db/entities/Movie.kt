package com.vodrex.mdb.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ensar.tmdbkotlin.db.GenreTypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity()
data class Movie(
        @PrimaryKey()
        @SerializedName("id")
        @Expose
        var id: Int?,

        @SerializedName("title")
        @Expose
        var title: String?,

        @SerializedName("vote_average")
        @Expose
        var voteAverage: String?,

        @SerializedName("genre_ids")
        @Expose
        @TypeConverters(GenreTypeConverters::class)
        var genreIds: List<Int>?,

        @Expose(serialize = false)
        var genres: String?,

        @SerializedName("backdrop_path")
        @Expose
        var backdropPath: String?,

        @SerializedName("release_date")
        @Expose
        var releaseDate: String?,

        @SerializedName("overview")
        @Expose
        var overview: String?,

        @SerializedName("poster_path")
        @Expose
        var posterPath: String?,

        @SerializedName("list_type")
        @Expose
        var listType: String?,

        @ColumnInfo(name = "orderId")
        var orderId: Int )