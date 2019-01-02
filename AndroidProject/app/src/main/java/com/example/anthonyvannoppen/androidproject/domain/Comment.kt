package com.example.anthonyvannoppen.androidproject.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
class Comment(
    @field:Json(name = "CommentID") val id: String,
    @field:Json(name = "Op") val op: String,
    @field:Json(name = "Tekst") val tekst: String,
    @field:Json(name = "MemeID") val meme: String
) : Parcelable, Serializable