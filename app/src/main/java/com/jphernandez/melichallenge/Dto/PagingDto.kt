package com.jphernandez.melichallenge.Dto

data class PagingDto (
    val total : Int?,
    val primary_results : Int?,
    val offset : Int?,
    val limit : Int?
)