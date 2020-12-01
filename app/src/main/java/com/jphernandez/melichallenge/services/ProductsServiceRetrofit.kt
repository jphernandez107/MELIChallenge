package com.jphernandez.melichallenge.services

import com.jphernandez.melichallenge.Dto.SearchResultDto
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsServiceRetrofit {

//    @GET("/sites/MLA/search?q={query}&offset={offset}")
//    fun getProductsList(@Path("query") querySearch: String, @Path("offset") offsetPage: Int = 0): Observable<SearchResultDto>
    @GET("/sites/MLA/search?")
    fun getProductsList(@Query("q") querySearch: String): Observable<SearchResultDto>


    companion object {
        fun create(retrofit: Retrofit): ProductsServiceRetrofit =
            retrofit.create(ProductsServiceRetrofit::class.java)
    }

}