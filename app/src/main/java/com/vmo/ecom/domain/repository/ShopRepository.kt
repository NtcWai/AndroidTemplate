package com.vmo.ecom.domain.repository

import com.vmo.ecom.data.util.Resource
import com.vmo.ecom.domain.model.ShopDomain

interface ShopRepository {
    suspend fun getShops(): Resource<MutableList<ShopDomain>>
}