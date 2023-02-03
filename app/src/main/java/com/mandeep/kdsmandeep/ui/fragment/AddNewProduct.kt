package com.mandeep.kdsmandeep.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mandeep.kdsmandeep.R
import com.mandeep.kdsmandeep.data.local.Product
import com.mandeep.kdsmandeep.data.local.Status
import com.mandeep.kdsmandeep.data.repository.LocalRepository
import com.mandeep.kdsmandeep.databinding.FragmentAddNewProductBinding
import com.mandeep.kdsmandeep.ui.util.showErrorMessage
import com.mandeep.kdsmandeep.ui.util.showMessage
import com.mandeep.kdsmandeep.ui.util.toEditable
import com.mandeep.kdsmandeep.ui.viewwmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class AddNewProduct : Fragment() {

    @Inject
    lateinit var localRepository: LocalRepository
    private val viewModel: AppViewModel by viewModels()

    private val TAG = AddNewProduct::class.java.simpleName
    private var _binding: FragmentAddNewProductBinding? = null
    private val binding get() = _binding!!
    private val args: AddNewProductArgs by navArgs()
    private lateinit var currentProduct: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddNewProductBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setObservers() {
        viewModel.product.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.INSERT -> userExit("Product Added")
                else -> clearError()
            }
        }

        viewModel.findProduct.observe(viewLifecycleOwner){
            when (it.status) {
                Status.RETRIVE -> getProductDetails(it.data as Product?)
                Status.UPDATE -> userExit("Product Update")
                else -> Log.d(TAG, "setObservers() called")
            }
        }
    }

    private fun getProductDetails(data: Product?) {
        Log.d(TAG, "getProductDetails() called with: data = $data")
        binding.apply {
            data.let {
                if (it != null) {
                    currentProduct = it
                }
                btnUpdateProduct.visibility = View.VISIBLE
                btnAddProduct.visibility = View.GONE
                tvName.text = it?.name?.toEditable()
                tvPrice.text = it?.price?.toEditable()
                tvCoupon.text = it?.coupon?.toEditable()
                tvDiscount.text = it?.discount?.toEditable()
            }
        }

    }


    private fun clearError() {
        binding.apply {
            txtInputPrice.error = null
            txtInputName.error = null
            txtInputCoupon.error = null
            txtInputDiscount.error = null

        }
    }

    private fun userExit(s: String) {
        binding.root.showMessage(s)
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                findNavController().popBackStack()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.prodId?.let {
            Toast.makeText(requireContext(), "ID: ${it}", Toast.LENGTH_LONG).show()
            getProductById(it)
        }
        setObservers()
        binding.btnAddProduct.setOnClickListener {
            clearError()

            if (validateInputFields()) {
                binding.apply {
                    viewModel.addProduct(
                        Product(
                            id = 0,
                            name = tvName.text.toString(),
                            price = tvPrice.text.toString(),
                            discount = tvDiscount.text.toString(),
                            coupon = tvCoupon.text.toString(),

                            )
                    )

                }
            }

        }

        binding.btnUpdateProduct.setOnClickListener {
            clearError()

            if (validateInputFields()) {
                binding.apply {
                    viewModel.updateProduct(
                        Product(
                            id = currentProduct.id,
                            name = tvName.text.toString(),
                            price = tvPrice.text.toString(),
                            discount = tvDiscount.text.toString(),
                            coupon = tvCoupon.text.toString(),

                            )
                    )

                }
            }

        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun validateInputFields(): Boolean {
        binding.apply {
            if (tvName.text.isNullOrEmpty()) {
                txtInputName showErrorMessage resources.getString(R.string.error_empty_name)
                return false
            }  else if (tvPrice.text.isNullOrEmpty()) {
                txtInputPrice showErrorMessage resources.getString(R.string.error_empty_price)
                return false
            } else if (tvCoupon.text.isNullOrEmpty()) {
                txtInputCoupon showErrorMessage resources.getString(R.string.error_empty_coupon)
                return false
            } else if (tvDiscount.text.isNullOrEmpty()) {
                txtInputDiscount showErrorMessage resources.getString(R.string.error_empty_discount)
                return false
            }  else return true
        }
    }

    private fun getProductById(id: String) {
        viewModel.productById(id)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProducts()
    }

}