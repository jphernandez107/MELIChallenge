package com.jphernandez.melichallenge.data

class ProductPicture (
    val id: String?,
    val url: String?,
    val secureUrl: String?,
    val size: String?,
    val maxSize :String?,
    val quality: String?
) {
    fun getURL() = secureUrl ?: url
}