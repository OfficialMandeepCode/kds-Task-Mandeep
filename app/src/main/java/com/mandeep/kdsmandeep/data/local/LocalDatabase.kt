package com.mandeep.kdsmandeep.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * App Name: KDS Mandeep
 * Package: com.mandeep.kdsmandeep.data.local
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 03 Feb, 2023
 **/
@Database(entities = [Product::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun userDao(): ProductDao
}