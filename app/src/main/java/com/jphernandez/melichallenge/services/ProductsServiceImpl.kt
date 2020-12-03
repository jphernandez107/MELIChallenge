package com.jphernandez.melichallenge.services

import com.jphernandez.melichallenge.Dto.ProductResultDto
import com.jphernandez.melichallenge.Dto.SearchResultDto
import io.reactivex.Observable

class ProductsServiceImpl(val retrofit: ProductsServiceRetrofit): ProductsService {

    override fun getProducts(searchQuery: String, offsetPage: Int): Observable<SearchResultDto> = retrofit.getProductsList(searchQuery)

    override fun getProductById(productId: String): Observable<List<ProductResultDto>> {
        return retrofit.getProductById(productId)
    }

}