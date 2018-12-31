package com.example.anthonyvannoppen.androidproject.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
class Meme(@field:Json(name = "MemeID")val id: String,
    @field:Json(name = "Op")val op: String,
    @field:Json(name = "Titel")val titel: String,
    @field:Json(name = "Afbeelding")val afbeelding: String,
    @field:Json(name = "Beschrijving")val beschrijving: String,
    @field:Json(name = "Categorie")val categorie: String,
    @field:Json(name = "Comments")var comments: List<Comment>): Parcelable,Serializable