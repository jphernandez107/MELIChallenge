package com.jphernandez.melichallenge

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jphernandez.melichallenge.repositories.ProductsRepository

class ProductListVM(val productsRepository: ProductsRepository? = null): ViewModel() {

    val productsLiveData: MutableLiveData<List<Product>> = MutableLiveData()

    fun getProducts(searchQuery: String) {
        productsRepository?.getProducts(searchQuery)?.subscribe {
            productsLiveData.postValue(it)
        }
        Log.d("ProductListVM", "Se presiono ok con el texto: $searchQuery")
    }

}



inline fun <reified T : ViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this, ViewModelFactory(creator)).get(T::class.java)
}

class ViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }

}