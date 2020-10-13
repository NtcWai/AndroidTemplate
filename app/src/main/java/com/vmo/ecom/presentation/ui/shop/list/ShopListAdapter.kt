package com.vmo.ecom.presentation.ui.shop.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vmo.ecom.R
import com.vmo.ecom.domain.model.ShopDomain
import com.vmo.ecom.presentation.util.loadImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_shop_list.view.*


class ShopListAdapter(
    private val onItemClicked: (ShopDomain) -> Unit
) : ListAdapter<ShopDomain, RecyclerView.ViewHolder>(ShopDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val shop = getItem(position)
        (holder as ShopViewHolder).run {
            bind(shop)
            itemView.setOnClickListener { onItemClicked.invoke(shop) }
        }
    }

    class ShopViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        companion object {
            fun create(parent: ViewGroup): ShopViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_shop_list, parent, false)
                return ShopViewHolder(view)
            }
        }

        fun bind(shopDomain: ShopDomain) {
            itemView.tvName.text = shopDomain.name
            itemView.imgThumb.loadImage(shopDomain.thumbUrl)
            itemView.tvStatus.run {
                if (shopDomain.isOpen) {
                    text = resources.getString(R.string.open)
                    setTextColor(ContextCompat.getColor(context, R.color.colorGreen))
                } else {
                    text = resources.getString(R.string.close)
                    setTextColor(ContextCompat.getColor(context, R.color.colorGray))
                }
            }
            itemView.imgStatus.isEnabled = shopDomain.isOpen
            itemView.tvDescription.text = shopDomain.description
        }
    }

}
