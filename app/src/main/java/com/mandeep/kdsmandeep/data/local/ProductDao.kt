package com.mandeep.kdsmandeep.data.local

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * App Name: KDS Mandeep
 * Package: com.mandeep.kdsmandeep.data.local
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 03 Feb, 2023
 **/
@Dao
interface ProductDao {
    @WorkerThread
    @Insert
    suspend fun insertProductData(product: Product):Long

    @WorkerThread
    @Update
    suspend fun updateProductData(product: Product)

    @WorkerThread
    @Delete
    suspend fun deleteProductData(product: Product)

    @WorkerThread
    @Query("SELECT * FROM ${RoomDbConstant.TABLE_NAME}")
    suspend fun getAllProductData(): List<Product>

    @WorkerThread
    @Query("SELECT * FROM ${RoomDbConstant.TABLE_NAME} where ${RoomDbConstant.PRODUCT_ID} = :id")
    suspend fun getProductDetails(id: String,): Product

}