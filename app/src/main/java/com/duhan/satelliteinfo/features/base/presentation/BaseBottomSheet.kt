package com.duhan.satelliteinfo.features.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.duhan.satelliteinfo.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet<BUE : BottomSheetEvent, BUS : BottomSheetState, VM : BottomSheetViewModel<BUE, BUS>, DB : ViewDataBinding> :
    BottomSheetDialogFragment(), FragmentInitializer<DB, BUE, BUS, VM> {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return onOnCreateViewTask(viewLifecycleOwner, requireArguments(), inflater, container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onDestroy() {
        onDestroyViewTask()
        super.onDestroy()
    }
}
