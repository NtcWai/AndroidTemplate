package com.vmo.ecom.presentation.ui.shop.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmo.ecom.data.util.Resource
import com.vmo.ecom.domain.model.ShopDomain
import com.vmo.ecom.domain.repository.ShopRepository
import kotlinx.coroutines.launch

class ShopListViewModel(private val shopRepository: ShopRepository): ViewModel() {
    var shopListResource = MutableLiveData<Resource<MutableList<ShopDomain>>>(Resource.initalize())

    fun fetchShopList() {
        shopListResource.value = Resource.loading()
        viewModelScope.launch {
            shopListResource.value = shopRepository.getShops()
        }
    }
}