package com.vmo.ecom.data.remote.response

import com.google.gson.annotations.SerializedName

data class ShopListResponse(
    @SerializedName("restaurants")
    val restaurants: List<Restaurant>,
    @SerializedName("timestamp")
    val timestamp: Int
) {
    data class Restaurant(
        @SerializedName("name")
        val name: String,
        @SerializedName("operatingHours")
        val operatingHours: String
    )
}
