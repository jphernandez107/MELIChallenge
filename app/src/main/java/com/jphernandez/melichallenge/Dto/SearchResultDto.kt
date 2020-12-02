package com.jphernandez.melichallenge.Dto

data class SearchResultDto (
    val site_id : String?,
    val query : String?,
    val paging : PagingDto?,
    val results : List<ProductDto>,
    val secondary_results : List<String?>?,
    val related_results : List<String?>?,
    val sort : SortDto?
    )