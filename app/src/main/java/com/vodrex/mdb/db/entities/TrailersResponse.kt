package com.vodrex.mdb.db.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailersResponse(
        @SerializedName("results")
        @Expose
        var trailers: List<Trailer>)