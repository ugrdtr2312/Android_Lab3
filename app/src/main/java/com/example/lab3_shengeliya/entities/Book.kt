package com.example.lab3_shengeliya.entities

class Book {
    var id : Int = 0
    var author : String = ""
    var publishedIn : String = ""

    constructor()

    constructor(author : String, publishedIn : String)
    {
        this.author = author
        this.publishedIn = publishedIn
    }
}