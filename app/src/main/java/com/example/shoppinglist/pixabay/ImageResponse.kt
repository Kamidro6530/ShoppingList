package com.example.shoppinglist.pixabay

data class ImageResponse(
    val imageSize: Int,
    val largeImageURL: String,
    val previewURL: String,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
)