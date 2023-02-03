package com.mandeep.kdsmandeep.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.kdsmandeep.data.local.Product
import com.mandeep.kdsmandeep.databinding.ItemProductBinding

/**
 * App Name: KDS Mandeep
 * Package: com.mandeep.kdsmandeep.ui.adapter
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 03 Feb, 2023
 **/
class ProductAdapter(val onClick: (prodId: Int) -> Unit, val onDelete:(Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.MyViewHolder>(ProductDiffUtil) {

    private val TAG = ProductAdapter::class.java.simpleName

    inner class MyViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    object ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = getItem(position)
        Log.d(TAG, "onBindViewHolder() called with: holder = $product, position = $position")
        holder.binding.apply {
            tvProdName.text = product.name
            tvProdPrice.text = product.price
            tvCoupon.text = product.coupon
            tvDiscount.text = product.discount

            ivDelete.setOnClickListener {
               onDelete.invoke(product)
            }

            ivEdit.setOnClickListener {
                onClick.invoke(product.id)
            }
        }

    }

}