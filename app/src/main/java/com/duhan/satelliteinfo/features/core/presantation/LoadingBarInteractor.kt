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
        loadingBarContainerId: Int,
        invisibleContainerId: Int = 0
    )

    fun onStateChange(uiState: US)
}

class LoadingBarInteractorImpl<US> : LoadingBarInteractor<US> {
    private var invisibleContainerId = 0
    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ViewDataBinding
    private var loadingBarContainerId: Int = 0
    private var loadingState: US? = null
    override fun onStateChange(uiState: US) {
        if (loadingState == null) return
        if (loadingBarContainerId == 0) return
        val fragmentContainerView = binding.root.findViewById<View>(loadingBarContainerId)
        val invisibleContainerView =
            if (invisibleContainerId != 0) binding.root.findViewById<View>(invisibleContainerId) else null
        if (fragmentContainerView !is FragmentContainerView) return
        with(fragmentContainerView) {
            if (loadingState == uiState) {
                visibility = View.VISIBLE
                invisibleContainerView.let { visibility = View.GONE }
                if (parent != null) {
                    fragmentManager.beginTransaction().add(
                        loadingBarContainerId,
                        LoadingFragment::class.java,
                        Bundle(),
                        "loadingBar",
                    ).commit()
                }
            } else {
                invisibleContainerView.let { visibility = View.VISIBLE }
                fragmentContainerView.visibility = View.GONE
                fragmentManager.popBackStack()
            }
        }
    }

    override fun registerLoadingBar(
        supportFragmentManager: FragmentManager,
        loadingState: US,
        viewDataBinding: ViewDataBinding,
        loadingBarContainerId: Int,
        invisibleContainerId: Int
    ) {
        this.fragmentManager = supportFragmentManager
        this.loadingState = loadingState
        this.binding = viewDataBinding
        this.loadingBarContainerId = loadingBarContainerId
        this.invisibleContainerId = invisibleContainerId
    }

}
