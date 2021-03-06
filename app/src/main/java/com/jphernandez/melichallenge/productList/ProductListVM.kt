package com.jphernandez.melichallenge.productList

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jphernandez.melichallenge.data.Product
import com.jphernandez.melichallenge.repositories.ProductsRepository

class ProductListVM(private val productsRepository: ProductsRepository): ViewModel() {

    val productsLiveData: MutableLiveData<List<Product>> = MutableLiveData()

    fun requestProducts(searchQuery: String) {
        productsRepository.getProducts(searchQuery).subscribe {
            productsLiveData.postValue(it)
            Log.d("ProductListVM", "Lista de productos: $it")
        }
    }

}



inline fun <reified T : ViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this,
            ViewModelFactory(creator)
        ).get(T::class.java)
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }

}