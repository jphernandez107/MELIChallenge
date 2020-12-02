package com.jphernandez.melichallenge.data

data class Product (
    val id: String?,
    val siteId: String?,
    val title: String?,
    val price: Float?,
    val currencyId: String?,
    val availableQuantity: Long?,
    val soldQuantity: Long?,
    val buyingMode: String?,
    val condition: String?,
    val permalink: String?,
    val thumbnailId: String?,
    val thumbnail: String?,
    val categoryId: String?,
    val officialStoreId: Long?,
    val domainId: String?,
    val tags : List<String>?,
    val pictures : List<ProductPicture>? = null,
    val warranty: String? = null,
    val isFreeSheeping: Boolean = false,
    val attributes: List<ProductAttribute>? = null
)