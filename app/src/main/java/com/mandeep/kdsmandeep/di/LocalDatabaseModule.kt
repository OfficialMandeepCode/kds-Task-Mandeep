package com.mandeep.kdsmandeep.di

import android.content.Context
import androidx.room.Room
import com.mandeep.kdsmandeep.data.local.LocalDatabase
import com.mandeep.kdsmandeep.data.local.ProductDao
import com.mandeep.kdsmandeep.data.local.RoomDbConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * App Name: KDS Mandeep
 * Package: com.mandeep.kdsmandeep.di
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 03 Feb, 2023
 **/
@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Provides
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(context, LocalDatabase::class.java, RoomDbConstant.TABLE_NAME)
            .build()
    }

    @Provides
    fun provideUserDao(localDatabase: LocalDatabase):ProductDao{
        return localDatabase.userDao()
    }

}