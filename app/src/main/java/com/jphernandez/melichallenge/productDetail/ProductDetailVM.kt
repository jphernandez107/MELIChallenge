package com.jphernandez.melichallenge.productDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jphernandez.melichallenge.Product
import com.jphernandez.melichallenge.repositories.ProductsRepository

class ProductDetailVM(private val productsRepository: ProductsRepository) : ViewModel() {

    val productLiveData: MutableLiveData<Product> = MutableLiveData()

    fun getProductById(productId: String) {
        productsRepository.getProductById(productId).subscribe{
            productLiveData.postValue(it)
            Log.d("ProductDetailVM", "Producto: $it")
        }
    }
}