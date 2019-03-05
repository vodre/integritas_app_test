package com.vodrex.mdb.db.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vodrex.mdb.db.entities.Genre

data class GenresResponse(
        @SerializedName("genres")
        @Expose
        var genres: List<Genre>)