package com.jphernandez.melichallenge.application

import com.jphernandez.melichallenge.productDetail.ProductDetailFragment
import com.jphernandez.melichallenge.productList.ProductListFragment
import com.jphernandez.melichallenge.repositories.ProductsRepository
import com.jphernandez.melichallenge.repositories.ProductsRepositoryImpl
import com.jphernandez.melichallenge.services.ProductsService
import com.jphernandez.melichallenge.services.ProductsServiceImpl
import com.jphernandez.melichallenge.services.ProductsServiceRetrofit
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class Providers {

    @Provides
    @Singleton
    fun providesRetrofit2(): Retrofit {
        val serverUrl = "https://api.mercadolibre.com"
        val httpClient = OkHttpClient.Builder()

        return Retrofit.Builder()
            .baseUrl(serverUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun productServiceProvider(retrofit: Retrofit): ProductsService =
        ProductsServiceImpl(ProductsServiceRetrofit.create(retrofit))


    @Provides
    @Singleton
    fun productsRepositoryProvider(productsService: ProductsService): ProductsRepository =
        ProductsRepositoryImpl(productsService)

}


@Singleton
@Component(modules = [Providers::class])
interface AppComponent {
    fun inject(productsListFragment: ProductListFragment)
    fun inject(productDetailFragment: ProductDetailFragment)
}