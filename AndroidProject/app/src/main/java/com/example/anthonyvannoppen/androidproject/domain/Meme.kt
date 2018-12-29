package com.example.anthonyvannoppen.androidproject.domain

import java.io.Serializable


class Meme(
           val op: String,
           val titel: String,
           val afbeelding: String,
           val beschrijving: String,
           val categorie: String,
           var comments: List<Comment>): Serializable