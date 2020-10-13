package com.vmo.ecom.presentation.ui.shop.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vmo.ecom.R
import com.vmo.ecom.data.util.Status
import com.vmo.ecom.presentation.base.BaseFragment
import com.vmo.ecom.presentation.navigation.navigateToDetail
import com.vmo.ecom.presentation.util.showToast
import kotlinx.android.synthetic.main.fragment_shop_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopListFragment : BaseFragment() {

    private lateinit var shopListAdapter: ShopListAdapter
    private val shopViewModel by viewModel<ShopListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shopListAdapter = ShopListAdapter(
            onItemClicked = {
                navigateToDetail(it)
            }
        )
    }

    override fun onInitializeView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_list, container, false)
    }

    override fun onViewInitialized(
        view: View,
        savedInstanceState: Bundle?,
        isViewCreated: Boolean
    ) {
        if (!isViewCreated) {
            initView()
            fetchData()
        }
        if (!shopViewModel.shopListResource.hasActiveObservers()) {
            initViewModel()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initView() {
        shopRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shopListAdapter
        }
        swipeRefreshLayout.setOnRefreshListener { fetchData() }
    }

    private fun initViewModel() {
        shopViewModel.shopListResource.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.INITIALIZE -> {

                }
                Status.LOADING -> {
                    swipeRefreshLayout.isRefreshing = true
                }
                Status.ERROR -> { // should parse error to check more detail
                    context?.showToast(getString(R.string.error_no_network_message))
                    swipeRefreshLayout.isRefreshing = false
                }
                Status.SUCCESS -> {
                    swipeRefreshLayout.isRefreshing = false
                    if (it.data.isNullOrEmpty()) {
                        context?.showToast(getString(R.string.empty_message)) // should display via TextView
                        shopListAdapter.submitList(mutableListOf())
                    } else {
                        shopListAdapter.submitList(it.data)
                    }
                }
            }
        })
    }

    private fun fetchData() {
        shopViewModel.fetchShopList()
    }
}
