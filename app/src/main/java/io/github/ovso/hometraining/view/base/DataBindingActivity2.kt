package io.github.ovso.hometraining.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.hometraining.BR

abstract class DataBindingActivity2<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    private val viewModelCls: Class<out ViewModel>
) : AppCompatActivity() {

    protected val binding: T by lazy {
        DataBindingUtil.setContentView<T>(this, layoutResId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        val owner = this@DataBindingActivity2
        with(binding) {
            lifecycleOwner = owner
            setVariable(BR.viewModel, ViewModelProvider(owner)[viewModelCls])
            executePendingBindings()
        }
    }
}
/*
protected val binding2 by lazy {
    DataBindingUtil.bind<T>(
        (window.decorView.findViewById(android.R.id.content) as ViewGroup).getChildAt(
            0
        )
    )!!
}
*/
