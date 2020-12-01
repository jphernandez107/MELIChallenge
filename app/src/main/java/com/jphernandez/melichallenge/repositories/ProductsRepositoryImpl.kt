package com.jphernandez.melichallenge.repositories

import com.jphernandez.melichallenge.Dto.ProductDto
import com.jphernandez.melichallenge.Dto.SearchResultDto
import com.jphernandez.melichallenge.Product
import com.jphernandez.melichallenge.services.ProductsService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class ProductsRepositoryImpl(private val productsService: ProductsService): ProductsRepository {


    override fun getProducts(searchQuery: String, offsetPage: Int): Observable<List<Product>> =
        productsService.getProducts(searchQuery, offsetPage)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map {
                convertProducts(it)
            }


    private fun convertProducts(searchResultDto: SearchResultDto) =
        searchResultDto.results.map(::convertProduct)

    private fun convertProduct(prod: ProductDto) =
        Product(
            prod.id,
            prod.siteId,
            prod.title,
            prod.price,
            prod.currencyId,
            prod.availableQuantity,
            prod.soldQuantity,
            prod.buyingMode,
            prod.condition,
            prod.permalink,
            prod.thumbnail,
            prod.categoryId,
            prod.officialStoreId,
            prod.domainId,
            prod.tags
        )
}