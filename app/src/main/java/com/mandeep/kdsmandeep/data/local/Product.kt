package com.mandeep.kdsmandeep.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * App Name: KDS Mandeep
 * Package: com.mandeep.kdsmandeep.data.local
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 03 Feb, 2023
 **/
@Entity(tableName = RoomDbConstant.TABLE_NAME)
data class Product(
    @NotNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = RoomDbConstant.PRODUCT_ID) val id: Int = 0,
    @NotNull @ColumnInfo(name = RoomDbConstant.PRODUCT_NAME) val name: String = "",
    @NotNull @ColumnInfo(name = RoomDbConstant.PRODUCT_PRICE) val price: String = "",
    @NotNull @ColumnInfo(name = RoomDbConstant.DISCOUNT) val discount: String = "",
    @NotNull @ColumnInfo(name = RoomDbConstant.COUPON) val coupon: String = "",
)
