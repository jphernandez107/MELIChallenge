package com.jphernandez.melichallenge.Dto

data class ProductDto (
    val id: String?,
    val siteId: String?,
    val title: String?,
    val price: Float?,
    val currency_id: String?,
    val available_quantity: Long?,
    val sold_quantity: Long?,
    val buying_mode: String?,
    val condition: String?,
    val permalink: String?,
    val thumbnail_id: String?,
    val thumbnail: String?,
    val category_id: String?,
    val official_store_id: Long?,
    val domain_id: String?,
    val tags: List<String>?,
    val pictures : List<ProductPictureDto>? = null,
    val warranty: String? = null,
    val shipping: ShippingDto? = null,
    val attributes: List<AttributeDto>? = null
)