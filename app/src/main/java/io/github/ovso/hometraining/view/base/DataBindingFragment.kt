package io.github.ovso.hometraining.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

@Deprecated("DataBindingFragment instead DataBindingFragment2")
abstract class DataBindingFragment<T : ViewDataBinding, V : DisposableViewModel> : Fragment() {

  private lateinit var viewDataBinding: T

  @LayoutRes
  abstract fun getLayoutId(): Int

  abstract fun getVariableValue(): V

  abstract fun getVariableId(): Int

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
    return viewDataBinding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    with(viewDataBinding) {
      setVariable(getVariableId(), getVariableValue())
      lifecycleOwner = this@DataBindingFragment
      executePendingBindings()
    }
  }
}