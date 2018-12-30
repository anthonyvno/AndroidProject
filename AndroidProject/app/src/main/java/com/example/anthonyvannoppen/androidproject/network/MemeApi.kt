package com.example.anthonyvannoppen.androidproject.network

import com.example.anthonyvannoppen.androidproject.domain.Meme
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MemeApi {

    @GET("memes/{icao}")
    fun getMeme(@Path("icao") icao : String?): Observable<Meme>

    @GET("memes.json")
    fun getAllMemes(): Observable<List<Meme>>

}