package com.vmo.ecom.domain.model

import android.os.Parcelable
import com.vmo.ecom.constant.Constant
import com.vmo.ecom.data.remote.response.ShopListResponse
import com.vmo.ecom.presentation.util.TimeUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShopDomain(
    val name: String,
    val isOpen: Boolean,
    val thumbUrl: String,
    val workingWeekDays: MutableList<WorkingWeekDay>,
    val description: String = "123 ABC Street, Washington DC"
): Parcelable

@Parcelize
data class WorkingWeekDay(
    val weekDay: String,
    val indexOfWeek: Int,
    val openHour: String,
    val closeHour: String
): Parcelable

fun ShopListResponse.toDomain(): MutableList<ShopDomain> {
    val shopDomains = mutableListOf<ShopDomain>()
    restaurants.forEachIndexed { index, shop ->
        val thumbUrl = Constant.THUMB_URL + index
        val operatingHours = shop.operatingHours
        val workingWeekDays = TimeUtil.parseToWorkingDays(operatingHours)
        val isOpen = TimeUtil.getWorkingStatus(System.currentTimeMillis(), TimeUtil.getTimeZoneOffset(), workingWeekDays)
        val shopDomain = ShopDomain(shop.name, isOpen, thumbUrl, workingWeekDays)
        shopDomains.add(shopDomain)
    }
    return shopDomains
}
