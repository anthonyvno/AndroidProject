package com.example.anthonyvannoppen.androidproject.domain

import java.io.Serializable


class Meme(val id: String,
           val op: String,
           val titel: String,
           val afbeelding: String,
           val beschrijving: String,
           var comments: List<Comment>): Serializable