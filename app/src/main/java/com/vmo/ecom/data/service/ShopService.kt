package com.vmo.ecom.data.service

import com.vmo.ecom.data.remote.response.ShopListResponse
import retrofit2.http.GET

interface ShopService {
    @GET("homework")
    suspend fun getShopList(): ShopListResponse
}