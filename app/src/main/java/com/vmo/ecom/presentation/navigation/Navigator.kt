package com.vmo.ecom.presentation.navigation

import androidx.navigation.fragment.findNavController
import com.vmo.ecom.R
import com.vmo.ecom.domain.model.ShopDomain
import com.vmo.ecom.presentation.ui.shop.list.ShopListFragment
import com.vmo.ecom.presentation.ui.shop.list.ShopListFragmentDirections

fun ShopListFragment.navigateToDetail(shop: ShopDomain) {
    val navController = findNavController()
    if (navController.currentDestination?.id == R.id.shopListFragment) {
        val direction = ShopListFragmentDirections.actionToDetail(shop)
        navController.navigate(direction)
    }
}