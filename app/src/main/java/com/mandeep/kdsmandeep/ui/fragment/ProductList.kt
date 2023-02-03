package com.mandeep.kdsmandeep.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mandeep.kdsmandeep.data.local.Product
import com.mandeep.kdsmandeep.data.local.Status
import com.mandeep.kdsmandeep.databinding.FragmentProductListBinding
import com.mandeep.kdsmandeep.ui.adapter.ProductAdapter
import com.mandeep.kdsmandeep.ui.viewwmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductList : Fragment() {
    private val TAG = ProductList::class.java.simpleName
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AppViewModel by viewModels()

    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        setAdapters()
        setObservers()
        initUi()
        return binding.root
    }

    private fun initUi() {
        binding.fabAddNew.setOnClickListener {
            findNavController().navigate(ProductListDirections.actionHomeFragmentToAddNewProduct())
        }
    }

    private fun setAdapters() {
        productAdapter = ProductAdapter(
            {id ->

                        findNavController().navigate(ProductListDirections.actionHomeFragmentToAddNewProduct(
                            id.toString()
                        ))
                },{
                    viewModel.deleteProduct(it)

            }


        )
        binding.rcyProductList.adapter = productAdapter
    }

    private fun setObservers() {
        viewModel.product.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.RETRIVE_ALL -> setProductsList(it.data as List<Product>)
                Status.INSERT -> Log.d(TAG, "setObservers() called")
                Status.UPDATE -> Log.d(TAG, "setObservers() called")
                Status.CLEAR -> Log.d(TAG, "setObservers() called")
                Status.DELETE ->  viewModel.getAllProducts()
                Status.RETRIVE -> Log.d(TAG, "setObservers() called")
            }
        }
    }

    private fun setProductsList(products: List<Product>) {
        Log.d(TAG, "setProductsList() called ${products.toString()}")
        productAdapter.submitList(products)
        productAdapter.notifyDataSetChanged()
    }


    override fun onResume() {
        super.onResume()
        viewModel.getAllProducts()
    }

}