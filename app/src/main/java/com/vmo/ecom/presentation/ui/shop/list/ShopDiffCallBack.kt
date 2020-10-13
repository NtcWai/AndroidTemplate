package com.vmo.ecom.presentation.ui.shop.list

import androidx.recyclerview.widget.DiffUtil
import com.vmo.ecom.domain.model.ShopDomain

class ShopDiffCallBack: DiffUtil.ItemCallback<ShopDomain>() {

    override fun areItemsTheSame(oldItem: ShopDomain, newItem: ShopDomain): Boolean {
        return oldItem.name === newItem.name
    }

    override fun areContentsTheSame(oldItem: ShopDomain, newItem: ShopDomain): Boolean {
        return oldItem == newItem
    }
}