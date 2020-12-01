package com.jphernandez.melichallenge.Dto

data class SearchResultDto (
    val siteId : String?,
    val query : String?,
    val paging : PagingDto?,
    val results : List<ProductDto>,
    val secondaryResults : List<String?>?,
    val relatedResults : List<String?>?,
    val sort : SortDto?
    )