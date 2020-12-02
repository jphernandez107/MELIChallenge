package com.jphernandez.melichallenge.repositories

import com.jphernandez.melichallenge.Dto.ProductDto
import com.jphernandez.melichallenge.Dto.ProductPictureDto
import com.jphernandez.melichallenge.Dto.ProductResultDto
import com.jphernandez.melichallenge.Dto.SearchResultDto
import com.jphernandez.melichallenge.Product
import com.jphernandez.melichallenge.ProductPicture
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

    override fun getProductById(productId: String): Observable<Product> =
        productsService.getProductById(productId)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map {
                convertProductFromResult(it[0])
            }


    private fun convertProducts(searchResultDto: SearchResultDto) =
        searchResultDto.results.map(::convertProduct)

    private fun convertProductFromResult(productResultDto: ProductResultDto) =
        convertProduct(productResultDto.body)

    private fun convertProduct(prod: ProductDto) =
        Product(
            prod.id,
            prod.siteId,
            prod.title,
            prod.price,
            prod.currency_id,
            prod.available_quantity,
            prod.sold_quantity,
            prod.buying_mode,
            prod.condition,
            prod.permalink,
            prod.thumbnail_id,
            prod.thumbnail,
            prod.category_id,
            prod.official_store_id,
            prod.domain_id,
            prod.tags,
            convertProductPictures(prod.pictures),
            prod.warranty,
            prod.shipping?.free_shipping ?: false
        )

    private fun convertProductPictures(picturesDto: List<ProductPictureDto>?) =
        picturesDto?.map(::convertProductPicture)

    private fun convertProductPicture(pictureDto: ProductPictureDto) =
        ProductPicture(
            pictureDto.id,
            pictureDto.url,
            pictureDto.secure_url,
            pictureDto.size,
            pictureDto.maxSize,
            pictureDto.quality
        )
}