package com.example.anthonyvannoppen.androidproject.network

import com.example.anthonyvannoppen.androidproject.domain.Comment
import com.example.anthonyvannoppen.androidproject.domain.Meme
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface MemeApi {

   /* @GET("/api/memes/{icao}")
    fun getMeme(@Path("icao") icao : String?): Observable<Meme>*/

    @GET("/api/memes")
    fun getAllMemes(): Observable<List<Meme>>

    //@Headers("Content-Type: application/json;charset=utf-8")
    @FormUrlEncoded
    @POST("/api/memes")
    //fun addMeme(@Body meme: Meme): Observable<Meme>
    fun addMeme(@Field("Op") op: String,
                @Field("Titel") titel: String,
                @Field("Categorie") categorie: String,
                @Field("Beschrijving") beschrijving: String,
                @Field("Afbeelding") afbeelding: String): Observable<Meme>

    @FormUrlEncoded
    @POST("/api/comments")
    fun addComment(@Field("Op") op: String,
                   @Field("Tekst") tekst: String,
                   @Field("MemeID") id: Int): Observable<Comment>
    //fun addComment(@Body comment: Comment): Call<Comment>

}