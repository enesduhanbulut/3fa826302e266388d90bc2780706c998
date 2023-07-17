package com.duhan.satelliteinfo.features.core.presantation

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager

interface LoadingBarInteractor<US> {
    fun registerLoadingBar(
        supportFragmentManager: FragmentManager,
        loadingState: US,
        viewDataBinding: ViewDataBinding,
        loadingBarContainerId: Int
    )

    fun onStateChange(uiState: US)
}

class LoadingBarInteractorImpl<US> : LoadingBarInteractor<US> {
    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ViewDataBinding
    private var loadingBarContainerId: Int = 0
    private var loadingState: US? = null
    override fun onStateChange(uiState: US) {
        if (loadingState == null) return
        if (loadingBarContainerId == 0) return
        val fragmentContainerView = binding.root.findViewById<View>(loadingBarContainerId)
        if (fragmentContainerView !is FragmentContainerView) return
        with(fragmentContainerView) {
            if (loadingState == uiState) {
                visibility = View.VISIBLE
                if (parent != null) {
                    fragmentManager.beginTransaction().add(
                        loadingBarContainerId,
                        LoadingFragment::class.java,
                        Bundle(),
                        "loadingBar",
                    ).commit()
                }
            } else {
                fragmentContainerView.visibility = View.GONE
                fragmentManager.popBackStack()
            }
        }
    }

    override fun registerLoadingBar(
        supportFragmentManager: FragmentManager,
        loadingState: US,
        viewDataBinding: ViewDataBinding,
        loadingBarContainerId: Int
    ) {
        this.fragmentManager = supportFragmentManager
        this.loadingState = loadingState
        this.binding = viewDataBinding
        this.loadingBarContainerId = loadingBarContainerId
    }

}
