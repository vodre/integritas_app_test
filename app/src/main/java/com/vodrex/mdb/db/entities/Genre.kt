package com.vodrex.mdb.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity()
data class Genre(
        @PrimaryKey
        @SerializedName("id")
        @Expose
        var id: Int?,

        @SerializedName("name")
        @Expose
        var name: String?)