package com.vmo.ecom.data.repository

import android.content.Context
import com.vmo.ecom.data.service.ShopService
import com.vmo.ecom.data.util.Resource
import com.vmo.ecom.data.util.createResourceWithNetworkCheck
import com.vmo.ecom.domain.model.ShopDomain
import com.vmo.ecom.domain.model.toDomain
import com.vmo.ecom.domain.repository.ShopRepository

class ShopRepositoryImpl(
    private val context: Context,
    private val service: ShopService
) : ShopRepository {
    override suspend fun getShops(): Resource<MutableList<ShopDomain>> {
        return createResourceWithNetworkCheck(
            context,
            request = {
                service.getShopList()
            },
            parseResult = { response ->
                response.toDomain()
            }
        )
    }
}