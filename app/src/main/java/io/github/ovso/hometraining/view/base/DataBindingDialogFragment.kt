package io.github.ovso.hometraining.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R

abstract class DataBindingDialogFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    private val viewModelCls: Class<out ViewModel>
) : DialogFragment() {

    private lateinit var viewDataBinding: T

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupStyle()
    }

    private fun setupStyle() {
        setStyle(
            STYLE_NORMAL,
            R.style.AppTheme_NoActionBar
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val owner = this@DataBindingDialogFragment
        with(binding) {
            setVariable(BR.viewModel, ViewModelProvider(owner)[viewModelCls])
            lifecycleOwner = owner
            executePendingBindings()
        }
    }
}
