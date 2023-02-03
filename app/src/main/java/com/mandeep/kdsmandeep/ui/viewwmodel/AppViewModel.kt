package com.mandeep.kdsmandeep.ui.viewwmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandeep.kdsmandeep.data.local.Product
import com.mandeep.kdsmandeep.data.local.RoomResource
import com.mandeep.kdsmandeep.data.local.Status
import com.mandeep.kdsmandeep.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * App Name: KDS Mandeep
 * Package: com.mandeep.kdsmandeep.ui.viewwmodel
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 03 Feb, 2023
 **/

@HiltViewModel
class AppViewModel @Inject constructor(val localRepository: LocalRepository) : ViewModel() {
    private var _product: MutableLiveData<RoomResource<Any>> = MutableLiveData(RoomResource(Status.CLEAR,null))
    private var _findProduct: MutableLiveData<RoomResource<Any>> = MutableLiveData(RoomResource(Status.CLEAR,null))
    val product get() = _product
    val findProduct get() = _findProduct


    fun addProduct(Product: Product) {
        viewModelScope.launch {
            localRepository.addProduct(Product).let {
                _product.postValue(RoomResource(Status.INSERT, it))
            }
        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            localRepository.getAllProductList().let { ProductList ->
                _product.postValue(RoomResource(Status.RETRIVE_ALL, ProductList))
            }
        }
    }

    fun productById(id: String) {
        viewModelScope.launch {
            localRepository.getProductDetails(id).let { ProductList ->
                Log.d("model", "productById() called with: ProductList = $ProductList")
                _findProduct.postValue(RoomResource(Status.RETRIVE, ProductList))
            }
        }

    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            localRepository.deleteProduct(product).let { ProductList ->
                Log.d("model", "productById() called with: ProductList = $ProductList")
                _product.postValue(RoomResource(Status.DELETE, ProductList))
            }
        }

    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            localRepository.updateProduct(product).let { ProductList ->
                Log.d("model", "productById() called with: ProductList = $ProductList")
                _findProduct.postValue(RoomResource(Status.UPDATE, ProductList))
            }
        }
    }
}