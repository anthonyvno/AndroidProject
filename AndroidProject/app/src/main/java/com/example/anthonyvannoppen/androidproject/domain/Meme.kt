package com.example.anthonyvannoppen.androidproject.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
class Meme(
    @field:Json(name = "op")val op: String,
    @field:Json(name = "titel")val titel: String,
    @field:Json(name = "afbeelding")val afbeelding: String,
    @field:Json(name = "beschrijving")val beschrijving: String,
    @field:Json(name = "categorie")val categorie: String,
    @field:Json(name = "comments")var comments: List<Comment>): Parcelable,Serializable