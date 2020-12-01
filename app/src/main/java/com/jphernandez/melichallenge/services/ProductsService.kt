package com.jphernandez.melichallenge.services

import com.jphernandez.melichallenge.Dto.SearchResultDto
import io.reactivex.Observable

interface ProductsService {
    fun getProducts(searchQuery: String, offsetPage: Int = 0): Observable<SearchResultDto>
}