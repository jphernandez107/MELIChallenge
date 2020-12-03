package com.jphernandez.melichallenge.services

import com.jphernandez.melichallenge.Dto.ProductResultDto
import com.jphernandez.melichallenge.Dto.SearchResultDto
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsServiceRetrofit {

    @GET("/sites/MLA/search?")
    fun getProductsList(@Query("q") querySearch: String): Observable<SearchResultDto>

    @GET("/items?")
    fun getProductById(@Query("ids") id: String): Observable<List<ProductResultDto>>


    companion object {
        fun create(retrofit: Retrofit): ProductsServiceRetrofit =
            retrofit.create(ProductsServiceRetrofit::class.java)
    }

}