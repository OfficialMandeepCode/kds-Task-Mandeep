package com.mandeep.kdsmandeep.data.repository

import com.mandeep.kdsmandeep.data.local.Product
import com.mandeep.kdsmandeep.data.local.ProductDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * App Name: KDS Mandeep
 * Package: com.mandeep.kdsmandeep.data.repository
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 03 Feb, 2023
 **/
@Singleton
class LocalRepository @Inject constructor(private val productDao: ProductDao) {

    suspend fun addProduct(product: Product): Long {
        return productDao.insertProductData(product)
    }

    suspend fun getProductDetails(id: String): Product {
        return productDao.getProductDetails(id)
    }

    suspend fun getAllProductList(): List<Product> {
        return productDao.getAllProductData()
    }

    suspend fun updateProduct(product: Product) {
        productDao.updateProductData(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProductData(product)
    }

}