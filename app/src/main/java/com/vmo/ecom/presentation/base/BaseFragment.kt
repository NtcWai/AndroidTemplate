package com.vmo.ecom.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    private var cachedView: View? = null
    private var isViewCreated = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return cachedView ?: onInitializeView(inflater, container, savedInstanceState).also {
            cachedView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewInitialized(view, savedInstanceState, isViewCreated)
        if (!isViewCreated) {
            isViewCreated = true
        }
    }

    override fun getView(): View? {
        return super.getView() ?: cachedView
    }

    override fun onDestroyView() {
        view?.let {
            (it.parent as? ViewGroup)?.removeAllViews()
        }
        super.onDestroyView()
    }

    protected open fun onInitializeView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = null

    protected open fun onViewInitialized(
        view: View,
        savedInstanceState: Bundle?,
        isViewCreated: Boolean
    ) {
    }
}