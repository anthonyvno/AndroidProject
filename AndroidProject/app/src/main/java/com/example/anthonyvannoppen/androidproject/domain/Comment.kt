package com.example.anthonyvannoppen.androidproject.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
class Comment(
    @field:Json(name = "op")val op: String,
    @field:Json(name = "tekst")val tekst: String) : Parcelable,Serializable