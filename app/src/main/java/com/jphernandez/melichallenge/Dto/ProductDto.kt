package com.jphernandez.melichallenge.Dto

data class ProductDto (
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
    val thumbnail: String?,
    val categoryId: String?,
    val officialStoreId: Long?,
    val domainId: String?,
    val tags : List<String>?
)