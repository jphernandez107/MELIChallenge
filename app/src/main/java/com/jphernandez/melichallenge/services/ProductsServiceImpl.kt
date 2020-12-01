package com.jphernandez.melichallenge.services

import com.jphernandez.melichallenge.Dto.SearchResultDto
import io.reactivex.Observable
import javax.inject.Inject

class ProductsServiceImpl(val retrofit: ProductsServiceRetrofit): ProductsService {

    override fun getProducts(searchQuery: String, offsetPage: Int): Observable<SearchResultDto> = retrofit.getProductsList(searchQuery)

}