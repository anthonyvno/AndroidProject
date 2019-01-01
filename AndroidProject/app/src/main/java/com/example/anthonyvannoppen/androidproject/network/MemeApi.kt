package com.example.anthonyvannoppen.androidproject.network

import com.example.anthonyvannoppen.androidproject.domain.Meme
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MemeApi {

    @GET("/api/memes/{icao}")
    fun getMeme(@Path("icao") icao : String?): Observable<Meme>

    @GET("/api/memes")
    fun getAllMemes(): Observable<List<Meme>>

    @POST
    fun addMeme(@Body meme: Meme)

}