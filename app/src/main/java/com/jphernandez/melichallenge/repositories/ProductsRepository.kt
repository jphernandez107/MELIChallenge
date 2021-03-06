package com.jphernandez.melichallenge.repositories

import com.jphernandez.melichallenge.data.Product
import io.reactivex.Observable

interface ProductsRepository {

    fun getProducts(searchQuery: String, offsetPage: Int = 0): Observable<List<Product>>

    fun getProductById(productId: String): Observable<Product>

}