package com.jphernandez.melichallenge.repositories

import com.jphernandez.melichallenge.Product
import io.reactivex.Observable

interface ProductsRepository {

    fun getProducts(searchQuery: String, offsetPage: Int = 0): Observable<List<Product>>
}