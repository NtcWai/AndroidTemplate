package com.vmo.ecom.presentation.ui.shop.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vmo.ecom.R
import com.vmo.ecom.domain.model.ShopDomain
import com.vmo.ecom.presentation.util.loadImage
import kotlinx.android.synthetic.main.fragment_shop_detail.*

class ShopDetailFragment : Fragment() {
    private lateinit var shopDetail: ShopDomain
    private val safeVarargs: ShopDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shopDetail = safeVarargs.shopDetail
//        activity?.setStatusTransparent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBack.setOnClickListener { findNavController().popBackStack() }
        tvName.text = shopDetail.name
        imgThumb.loadImage(shopDetail.thumbUrl)
        tvStatus.run {
            if (shopDetail.isOpen) {
                text = resources.getString(R.string.open)
                setTextColor(ContextCompat.getColor(context, R.color.colorGreen))
            } else {
                text = resources.getString(R.string.close)
                setTextColor(ContextCompat.getColor(context, R.color.colorGray))
            }
        }
        imgStatus.isEnabled = shopDetail.isOpen
        tvDescription.text = shopDetail.description
        openingHourRecycler.adapter = OpeningHourAdapter(shopDetail.workingWeekDays)
    }
}
